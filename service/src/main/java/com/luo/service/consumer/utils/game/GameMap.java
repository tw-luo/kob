package com.luo.service.consumer.utils.game;

import java.util.Arrays;
import java.util.Random;

public class GameMap {
    private final int rows;
    private final int cols;
    private final int wallCount;
    private final int[][] map;

    private final static int[] dx = {-1, 0, 1, 0};
    private final static int[] dy = {0, 1, 0, -1};

    public GameMap(int rows, int cols, int wallCount) {
        this.rows = rows;
        this.cols = cols;
        this.wallCount = wallCount;
        this.map = new int[rows][cols];
        generateMap();
    }

    private void generateMap() {
        for (int i = 0; i < 1000; ++i) {
            if (generate()) {
                break;
            }
        }
    }

    private boolean generate() {
        //初始化地图
        for (int i = 0; i < rows; ++i) {
            Arrays.fill(map[i], 0);
        }

        // 给上下左右最外层加上障碍物
        for (int r = 0; r < this.rows; r++) {
            map[r][0] = map[r][this.cols - 1] = 1;
        }

        for (int c = 0; c < this.cols; c++) {
            map[0][c] = map[this.rows - 1][c] = 1;
        }

        // 创建随机障碍物
        Random random = new Random();
        for (int i = 0; i < this.wallCount / 2; i++) {
            for (int j = 0; j < 1000; j++) {
                int r = random.nextInt(this.rows);
                int c = random.nextInt(this.cols);
                if (map[r][c] == 1 || map[c][r] == 1) continue;

                if (this.calcDistance(r, c, this.rows - 2, 1) <= 2) continue;
                if (this.calcDistance(r, c, 1, this.cols - 2) <= 2) continue;

                if ((r == this.rows - 2 && c == 1) || (r == 1 && c == this.cols - 2))
                    continue;

                map[r][c] = map[c][r] = 1;
                break;
            }
        }

        //使用备份数组g判断是否连通
        int[][] g = new int[rows][cols];
        for (int i = 0; i < rows; ++i) {
            System.arraycopy(map[i], 0, g[i], 0, cols);
        }

        return checkConnectivity(g, rows - 2, 1, 1, cols - 2);
    }

    private int calcDistance(int sx, int sy, int tx, int ty) {
        return Math.abs(sx - tx) + Math.abs(sy - ty);
    }

    private boolean checkConnectivity(int[][] g, int sx, int sy, int tx, int ty) {
        if (sx == tx && sy == ty) return true;
        g[sx][sy] = 1;


        for (int i = 0; i < 4; i++) {
            int x = sx + dx[i];
            int y = sy + dy[i];
            if (g[x][y] == 0 && this.checkConnectivity(g, x, y, tx, ty)) return true;
        }

        return false;
    }

    public int[][] getMap() {
        return map;
    }

}
