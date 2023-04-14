const GAME_OBJECTS=[];

export class BaseObject{
    constructor(){
        GAME_OBJECTS.push(this);
        this.timedelta=0;
        this.isStart=false;
    }
    
    start(){//只有开始的时候调用一次
    }

    update(){//每一帧调用一次
    
    }

    onDestroy(){

    }

    destroy(){
        let index=GAME_OBJECTS.indexOf(this);
        if(index>-1){
            this.onDestroy();
            GAME_OBJECTS.splice(index,1);
        }
        
    }
}

let lastTimestamp=0;

const step=(timestamp)=>{
    for (let obj of GAME_OBJECTS) {
        if (!obj.isStart) {
            obj.isStart = true;
            obj.start();
        } else {
            obj.timedelta = timestamp - lastTimestamp;
            obj.update();
        }
    }

    lastTimestamp = timestamp;
    requestAnimationFrame(step);
}

requestAnimationFrame(step)