import smoke11.DebugView;
import smoke11.wc2utils.Vector2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: nao
 * Date: 27.04.13
 * Time: 17:49
 * To change this template use File | Settings | File Templates.
 */
///////
// idea of this class is to make proper spritesheets from broken ones as this one - http://spriters-resource.com/pc_computer/warcraft2/peon.png
// as you can see, sprites are not in equal distance from each other etc.
// BrokenSpriteSheetParser should convert it to usable good Spritesheet
// i`m writing this for parsing wc2spritesheets from site (http://spriters-resource.com/pc_computer/warcraft2)
// i`m assuming i will get sprites for each row at once
//////
public class BrokenSpriteSheetParser {
    public static void main(String argv[]) throws IOException {

        BufferedImage brokenSS = ImageIO.read(new File("D:/datafiles/sprites/orc/peon.png"));
        analize(brokenSS);
    }
    //////////////
    //First - there is data array which contains info if there is a nontransparent pixel for each pixel in image

    public static void analize(BufferedImage brokenSpriteSheet) throws IOException {

        int[][] imagedata = convertTo2DAlphaData(brokenSpriteSheet);
        int width = brokenSpriteSheet.getWidth();
        int height = brokenSpriteSheet.getHeight();
        //populate data array
        boolean[][] nontransparencyData = new boolean[width][height];
        for(int y=0;y<height;y++)
            for (int x=0;x<width;x++)
            {
                if(imagedata[x][y]!=0)
                    nontransparencyData[x][y]=true;
            }
        //now it is needed to analize data array
        //first, i`m getting rows that have any nontrasparent pixel and i will use them as first separation between sprites and to calculate size of y for sprites in same row
        ArrayList<Vector2> listOfSpriteRowsSize = getListOfSpriteRowsSize(nontransparencyData);

        //now, when i know where are sprite rows, i can get all of Sprite sizes/positions

    }
    //taken from http://stackoverflow.com/questions/6524196/java-get-pixel-array-from-image and modify for alpha data only
    private static int[][] convertTo2DAlphaData(BufferedImage image) {

        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        final int width = image.getWidth();
        final int height = image.getHeight();
        final boolean hasAlphaChannel = image.getAlphaRaster() != null;

        int[][] result = new int[width][height];
        if (hasAlphaChannel) {
            final int pixelLength = 4;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int alpha = 0;
                alpha += (((int) pixels[pixel] & 0xff) << 24); // alpha

                result[col][row] = alpha;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        } else {
            return null;
        }

        return result;
    }
    private static ArrayList<Vector2> getListOfSpriteRowsSize(boolean[][] data)
    {
        int width = data.length;
        int height=data[0].length;
        boolean[] cleanRows = new boolean[height]; //not using really, but it maybe useful some day
        boolean isClean=true;
        int starty=-1, endy=-1;
        ArrayList<Vector2> sizesOfSpriteY = new ArrayList<Vector2>();
        for(int y=0;y<height;y++)
        {
            isClean=true;
            for (int x=0;x<width;x++)
            {
                if(data[x][y])
                {
                    isClean=false;
                    if(starty<0) //its new sprite row
                    {
                        starty=y;
                        endy=-1;
                    }
                    break;
                }

            }
            if(isClean)
            {
                cleanRows[y]=true;
                if(endy<0) //found end of current sprite row
                    endy=y-1;
            }
            if(starty>=0&&endy>=0)  //found start and end, put it in list and reinit starty
            {
                sizesOfSpriteY.add(new Vector2(starty,endy));
                starty=-1;
            }

        }
        return sizesOfSpriteY;
    }
    private static HashMap<Vector2,Vector2> getListOfSpriteSizes(boolean[][] data, ArrayList<Vector2> listOfSpriteRowsSize)
    {
        int width = data.length;
        int height=data[0].length;
        boolean[] cleanColumns = new boolean[height]; //not using really, but it maybe useful some day
        boolean isClean=true;
        int startx=-1, endx=-1;
        HashMap<Vector2,Vector2> listOfSpriteSizes = new HashMap<Vector2,Vector2>();
        for(Vector2 Spriterow : listOfSpriteRowsSize)
        {
            for(int x=0;x<width;x++)
            {
                isClean=true;
                for (int y=Spriterow.x;y<Spriterow.y;y++)
                {
                    isClean=false;
                    if(startx<0) //its new sprite row
                    {
                        startx=y;
                        endx=-1;
                    }
                    break;
                }
                if(isClean)
                {
                    cleanColumns[x]=true;
                    if(endx<0) //found end of current sprite row
                        endx=x-1;
                }
                if(startx>=0&&endx>=0)  //found start and end, put it in list and reinit starty
                {
                    listOfSpriteSizes.put(Spriterow,new Vector2(startx,endx));
                    startx=-1;
                }
            }
        }
        return listOfSpriteSizes;
    }
}
