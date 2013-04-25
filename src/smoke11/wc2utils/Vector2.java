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
    {this.x=vect.x;this.y=vect.y; }

    public void sub(Vector2 vec2)
    { this.x-=vec2.x; this.y-=vec2.y;}
    public void add(Vector2 vec2)
    { this.x+=vec2.x; this.y+=vec2.y;}
    public void sub(int val)
    { this.x-=val; this.y-=val;}
    public void add(int val)
    { this.x+=val; this.y+=val;}
    public void mul(int val)
    { this.x*=val; this.y*=val;}
    public void div(int val)
    { this.x/=val; this.y/=val;}
    public double getLength()
    {  return Math.sqrt(this.x*this.x+this.y*this.y); }
    public void normalize()
    { this.x/=getLength(); this.y/=getLength();}
    public Vector2 copy()
    { return new Vector2(this.x,this.y); }

    public static Vector2 sub(Vector2 vec1, Vector2 vec2)
    {
        Vector2 newVec= new Vector2(vec1);
        newVec.sub(vec2);
        return newVec;
    }
    public static Vector2 add(Vector2 vec1, Vector2 vec2)
    {
        Vector2 newVec= new Vector2(vec1);
        newVec.add(vec2);
        return newVec;
    }
    public static Vector2 sub(Vector2 vec1, int val)
    {
        Vector2 newVec= new Vector2(vec1);
        newVec.sub(val);
        return newVec;
    }
    public static Vector2 add(Vector2 vec1, int val)
    {
        Vector2 newVec= new Vector2(vec1);
        newVec.add(val);
        return newVec;
    }
    public static Vector2 mul(Vector2 vec1, int val)
    {
        Vector2 newVec= new Vector2(vec1);
        newVec.mul(val);
        return newVec;
    }
    public static Vector2 div(Vector2 vec1, int val)
    {
        Vector2 newVec= new Vector2(vec1);
        newVec.div(val);
        return newVec;
    }
    public static Vector2 normalize(Vector2 vec1)
    {
        Vector2 newVec= new Vector2(vec1);
        newVec.normalize();
        return newVec;
    }

    public boolean compareVector2(Vector2 vec2) { return (this.x==vec2.x&&this.y==vec2.y);}

    @Override
    public String toString(){return "("+x+","+y+")";}
}
