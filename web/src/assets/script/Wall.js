import { BaseObject } from './BaseObject';
import Color from './utils/Color';

export class Wall extends BaseObject{
    constructor(r,c,gameMap){
        super();
        this.r=r;
        this.c=c;
        this.gameMap=gameMap;
    }

    update() {
        this.render();
    }

    render() {
        const L = this.gameMap.L;
        const ctx = this.gameMap.ctx;

        ctx.fillStyle = Color.wall;
        ctx.fillRect(this.c * L, this.r * L, L, L);
    }

}