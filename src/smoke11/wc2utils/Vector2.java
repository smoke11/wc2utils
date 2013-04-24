package smoke11.wc2utils;

/**
 * Created with IntelliJ IDEA.
 * User: tls
 * Date: 24.04.13
 * Time: 17:33
 * To change this template use File | Settings | File Templates.
 */
public class Vector2 {
    public int x;
    public int y;

    public Vector2()
    {x=0;y=0;}
    public Vector2(int x, int y)
    {this.x=x;this.y=y;}
    public Vector2(float x, float y)
    {this.x=(int)x;this.y=(int)y;}
    public Vector2(Vector2 vect)
    {this.x=vect.x;this.y=vect.y;}

    public void sub(Vector2 vec2)
    { this.x-=vec2.x; this.y-=vec2.y;}
    public void add(Vector2 vec2)
    { this.x+=vec2.x; this.y+=vec2.y;}
    public double getLength()
    {  return Math.sqrt(this.x*this.x+this.y*this.y); }
    public void normalize()
    { this.x/=getLength(); this.y/=getLength();}

    public boolean compareVector2f(Vector2 vec2) { return (this.x==vec2.x&&this.y==vec2.y);}

    @Override
    public String toString(){return "("+x+","+y+")";}
}
