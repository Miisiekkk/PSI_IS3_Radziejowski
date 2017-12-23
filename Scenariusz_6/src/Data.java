
public class Data {
    private double x [];
    private int xCount;
    private String y;

    public Data(double[] x, String y, int xCount) {
        this.x = x;
        this.y = y;
        this.xCount = xCount;
    }

    public Data(int xCount){
        this.x = new double[xCount];
        this.xCount = xCount;

    }

    public void setXi(int i, double x){
        if (i < xCount)
            this.x[i] = x;
    }

    public double getXi(int i){
        if(i < xCount)
            return this.x[i];
        else return 0;
    }

    public double[] getX() {
        return x;
    }

    public void setX(double[] x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public int getxCount() {
        return xCount;
    }

    public void setxCount(int xCount) {
        this.xCount = xCount;
    }
}