import { BaseObject } from "./BaseObject";
import Color from "./utils/Color";
import { Wall } from "./Wall";
import { Snake } from "./Snake";

export class GameMap extends BaseObject {
  constructor(ctx, parent) {
    super();
    this.ctx = ctx;
    this.parent = parent;

    this.L = 0;

    this.rows = 13;
    this.cols = 13;

    this.wallCount = 20;
    this.walls = [];

    this.snakes = [
      //蛇1初始位置为左下角
      new Snake(
        {
          id: 1,
          color: Color.snake1,
          r: this.rows - 2,
          c: 1,
        },
        this
      ),
      //蛇2初始位置为右上角
      new Snake(
        {
          id: 2,
          color: Color.snake2,
          r: 1,
          c: this.cols - 2,
        },
        this
      ),
    ];

    this.winner=null;
    this.message=null;
  }

  start() {
    for (let i = 0; ; ++i) {
      if (this.createWalls()) break;
    }
  }

  //判断随机生成的地图是否连通
  /**
   * @param {Array2D} g 地图
   * @param {int} sx 起点x
   * @param {int} sy 起点y
   * @param {int} tx 终点x
   * @param {int} ty 终点y
   * @returns {boolean} 是否连通
   * @memberof GameMap
   */
  checkConnectivity(g, sx, sy, tx, ty) {
    if (sx == tx && sy == ty) return true;
    g[sx][sy] = true;

    let dx = [-1, 0, 1, 0];
    let dy = [0, 1, 0, -1];
    for (let i = 0; i < 4; i++) {
      let x = sx + dx[i],
        y = sy + dy[i];
      if (!g[x][y] && this.checkConnectivity(g, x, y, tx, ty)) return true;
    }

    return false;
  }

  calcDistance(sx, sy, tx, ty) {
    return Math.abs(sx - tx) + Math.abs(sy - ty);
  }

  createWalls() {
    const g = [];
    for (let r = 0; r < this.rows; r++) {
      g[r] = [];
      for (let c = 0; c < this.cols; c++) {
        g[r][c] = false;
      }
    }

    // 给上下左右最外层加上障碍物
    for (let r = 0; r < this.rows; r++) {
      g[r][0] = g[r][this.cols - 1] = true;
    }

    for (let c = 0; c < this.cols; c++) {
      g[0][c] = g[this.rows - 1][c] = true;
    }

    // 创建随机障碍物
    for (let i = 0; i < this.wallCount / 2; i++) {
      for (let j = 0; j < 1000; j++) {
        let r = parseInt(Math.random() * this.rows);
        let c = parseInt(Math.random() * this.cols);
        if (g[r][c] || g[c][r]) continue;

        if (this.calcDistance(r, c, this.rows - 2, 1) <= 2) continue;
        if (this.calcDistance(r, c, 1, this.cols - 2) <= 2) continue;

        if ((r == this.rows - 2 && c == 1) || (r == 1 && c == this.cols - 2))
          continue;

        g[r][c] = g[c][r] = true;
        break;
      }
    }

    const copy_g = JSON.parse(JSON.stringify(g));
    if (!this.checkConnectivity(copy_g, this.rows - 2, 1, 1, this.cols - 2))
      return false;

    for (let r = 0; r < this.rows; r++) {
      for (let c = 0; c < this.cols; c++) {
        if (g[r][c]) {
          this.walls.push(new Wall(r, c, this));
        }
      }
    }

    return true;
  }

  updateSize() {
    this.L = parseInt(
      Math.min(
        this.parent.clientWidth / this.cols,
        this.parent.clientHeight / this.rows
      )
    );
    this.ctx.canvas.width = this.L * this.cols;
    this.ctx.canvas.height = this.L * this.rows;
    //console.log(this.L);
  }

  checkSnakeCollision() {
    const [snake1, snake2] = this.snakes;

    for (const cell1 of snake1.cells) {
      for (const cell2 of snake2.cells) {
        if (cell1.x === cell2.x && cell1.y === cell2.y) {
          if(cell1===snake1.cells[0]||cell2===snake2.cells[0]){
            snake1.status = "die";
            snake2.status = "die";
          }
          else if (snake1.status !== "die") {
            snake1.status = "die";
          }
          else if (snake2.status !== "die") {
            snake2.status = "die";
          }
          return;
        }
      }
    }
  }

  //判断游戏是否结束
  isGameOver() {
    return this.snakes.some((snake) => snake.status === "die");
  }

  updateGameStatus() {
    if (this.isGameOver()) {
      if(this.snakes[0].status==="die"&&this.snakes[1].status==="die"){
        this.winner="snake1&snake2";
      }
      else if(this.snakes[0].status==="die"){
        this.winner="snake2";
        this.snakes[1].status="idle";
      }
      else if(this.snakes[1].status==="die"){
        this.winner="snake1";
        this.snakes[0].status="idle";
      }
    }
    if(this.message===null&&this.winner!==null){
      this.message=`获胜者是: ${this.winner}`;
      alert(this.message);
    }
  }

  update() {
    this.updateSize();
    this.checkSnakeCollision();
    this.updateGameStatus();
    this.render();
  }

  onDestroy() {}

  render() {
    for (let r = 0; r < this.rows; r++) {
      for (let c = 0; c < this.cols; c++) {
        if ((r + c) % 2 == 0) {
          this.ctx.fillStyle = Color.evenSquare;
        } else {
          this.ctx.fillStyle = Color.oddSquare;
        }
        this.ctx.fillRect(c * this.L, r * this.L, this.L, this.L);
      }
    }
  }
}
