package huskylens.java;
public class Block {
    private int xPosition;
    private int yPosition;
    private int width;
    private int height;
    private int ID;
    private boolean isLearned;

    public Block(int xPosition, int yPosition, int width, int height, int ID){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
        this.ID = ID;
        if(ID > 0){
            this.isLearned = true;
        }else{
            this.isLearned = false;
        }
    }

    public int getXPosition(){
        return this.xPosition;
    }

    public int getYPosition(){
        return this.yPosition;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public int getId(){
        return this.ID;
    }

    public boolean getIsLearned(){
        return this.isLearned;
    }
}
