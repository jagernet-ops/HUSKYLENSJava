package huskylens.java;

public class Arrow {
    private int xTail;
    private int yTail;
    private int xHead;
    private int yHead;
    private int ID;
    private boolean isLearned;

    public Arrow(int xTail, int yTail, int xHead, int yHead, int ID){
        this.xTail = xTail;
        this.yTail = yTail;
        this.xHead = xHead;
        this.yHead = yHead;
        this.ID = ID;
        if(ID > 0){
            this.isLearned = true;
        }else{
            this.isLearned = false;
        }
    }

    public int getXTail(){
        return this.xTail;
    }

    public int getYTail(){
        return this.yTail;
    }

    public int getXHead(){
        return this.xHead;
    }

    public int getYHead(){
        return this.yHead;
    }

    public int getId(){
        return this.ID;
    }

    public boolean getIsLearned(){
        return this.isLearned;
    }
}
