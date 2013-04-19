package smoke11.wc2utils.pudparser;
/**
 * Created with IntelliJ IDEA.
 * User: nao
 * Date: 07.03.13
 * Time: 18:52
 * To change this template use File | Settings | File Templates.
 */
public class Tile {
    public String PudID;
    public int ID;
    public String Name; //name of tile, with of this type
    public int SizeX; //size of rect
    public int SizeY;
    public int OffsetX; //how get to this tile from spritesheet from left top point
    public int OffsetY; //how get to this tile from spritesheet from left top point
    //TODO: add information about which spritesheet is used
    public Tile(int id, String pudid, String name, int size, int offx, int offy)
    {
        ID=id;
        PudID = pudid;
        Name = name;
        SizeX = size;
        SizeY = size;
        OffsetX=offx;
        OffsetY=offy;
    }
    public Tile(int id, String pudid, String name, int sizex, int sizey, int offx, int offy)
    {
        ID=id;
        PudID = pudid;
        Name = name;
        SizeX = sizex;
        SizeY = sizey;
        OffsetX=offx;
        OffsetY=offy;
    }
}
