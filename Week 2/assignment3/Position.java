public class Position {
    // Declare Variables
    int posX;
    int posY;
    int center;

    public Position(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public boolean isCurrentPosition(int posX, int posY, int rowPosY) {
        // If it's not in the center row (due to padding) then we return early
        if (isCurrentX(posX) && isCurrentY(posY) && isSquareCenterY(rowPosY)) return true;
        return false;
    }

    public boolean isSquareCenterY(int posY) {
        if (this.center == posY) return true;
        return false;
    }

    public boolean isCurrentX(int posX) {
        if (this.posX == posX) return true;
        return false;
    }
    
    public boolean isCurrentY(int posY) {
        if (this.posY == posY) return true;
        return false;
    }

    // Getters & Setters
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int setSquareCenterY(int padding) {
        center = (padding + 1) / 2;
        return center;
    }

    public int getSquareCenterY() { return center; }
}
