import { BaseObject } from "./BaseObject";
import { Cell } from "./Cell";
import Color from "./utils/Color";

export class Snake extends BaseObject {
  constructor(info, gameMap) {
    super();
    this.id = info.id;
    this.color = info.color;

    this.gameMap = gameMap;

    this.cells = [new Cell(info.r, info.c)]; //存放蛇的身体,cells[0]为蛇头

    this.speed = 4; //蛇每秒移动的格子数

    //蛇的状态
    //idle 静止
    //die 死亡
    //move 移动
    this.status = "move";

    this.moveTimer = 0; //移动计时器

    this.moveCounter = 0; //移动计数器
    //蛇长度增长的间隔
    this.growInterval = 3;
  }

  /**
   * direction:蛇的移动方向
   * right:向右
   * left:向左
   * up:向上
   * down:向下
   */
  initDirection() {
    if (this.id == 1) {
      this.direction = "right";
    } else if (this.id == 2) {
      this.direction = "left";
    }
  }

  addListener() {
    if (this.id === 1) {
      window.addEventListener("keydown", (e) => {
        switch (e.key) {
          case "w":
            this.changeDirection("up");
            break;
          case "a":
            this.changeDirection("left");
            break;
          case "s":
            this.changeDirection("down");
            break;
          case "d":
            this.changeDirection("right");
            break;
        }
      });
    } else if (this.id === 2) {
      window.addEventListener("keydown", (e) => {
        switch (e.key) {
          case "ArrowUp":
            this.changeDirection("up");
            break;
          case "ArrowLeft":
            this.changeDirection("left");
            break;
          case "ArrowDown":
            this.changeDirection("down");
            break;
          case "ArrowRight":
            this.changeDirection("right");
            break;
        }
      });
    }
  }

  start() {
    this.initDirection();
    this.addListener();
  }

  changeDirection(newDirection) {
    const oppositeDirections = {
      left: "right",
      right: "left",
      up: "down",
      down: "up",
    };

    if (newDirection !== oppositeDirections[this.direction]) {
      this.direction = newDirection;
    }
  }

  updatePosition() {
    if (this.status !== "move") return;

    this.moveTimer += this.timedelta;

    if (this.moveTimer >= 1000 / this.speed) {
      this.moveTimer -= 1000 / this.speed;

      this.moveCounter++;

      // 在增加蛇的长度之前保存蛇尾的位置
      const tail = {
        r: this.cells[this.cells.length - 1].y - 0.5,
        c: this.cells[this.cells.length - 1].x - 0.5,
      };

      // 更新蛇身体的位置，从蛇尾到蛇头，依次将前一个格子的位置赋值给后一个格子
      for (let i = this.cells.length - 1; i > 0; i--) {
        this.cells[i].x = this.cells[i - 1].x;
        this.cells[i].y = this.cells[i - 1].y;
      }

      // 根据direction更新蛇头的位置
      if (this.direction === "right") {
        this.cells[0].x++;
      } else if (this.direction === "left") {
        this.cells[0].x--;
      } else if (this.direction === "up") {
        this.cells[0].y--;
      } else if (this.direction === "down") {
        this.cells[0].y++;
      }

      if (this.moveCounter % this.growInterval === 0) {
        this.cells.push(new Cell(tail.r, tail.c));
      }
    }
  }

  checkCollision() {
    const head = this.cells[0];

    // 检测蛇头是否碰到墙
    for (const wall of this.gameMap.walls) {
      if (head.x === wall.c + 0.5 && head.y === wall.r + 0.5) {
        this.status = "die";
        return;
      }
    }

    // 检测蛇头是否碰到自己的身体
    for (let i = 1; i < this.cells.length; i++) {
      if (head.x === this.cells[i].x && head.y === this.cells[i].y) {
        this.status = "die";
        return;
      }
    }
  }

  update() {
    this.updatePosition();
    this.checkCollision();
    this.render();
  }

  render() {
    const L = this.gameMap.L;
    const ctx = this.gameMap.ctx;

    //画蛇的身体
    ctx.fillStyle = this.color;
    if (this.status === "die") {
      ctx.fillStyle = Color.snakeDie;
    }

    for (const cell of this.cells) {
      ctx.beginPath();
      ctx.arc(cell.x * L, cell.y * L, (L / 2) * 0.8, 0, Math.PI * 2);
      ctx.fill();
    }

    //画蛇的眼睛
    ctx.fillStyle = Color.snakeEye;

    const head = this.cells[0];

    ctx.beginPath();
    ctx.arc(head.x * L, head.y * L, (L / 2) * 0.1, 0, Math.PI * 2);
    ctx.fill();
  }
}
