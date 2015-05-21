import java.awt.image.*;

public class Animation{

    private BufferedImage[] frames;
    private int currentImage;
   
    private long delay; 
    private long startTime;

    public Animation(){
	delay = 0;
	startTime = 0;
    }


    //load frames into animation
    public void setFrames(BufferedImage[] f){
	frames = f;
	if (currentImage >= frames.length)
	    currentImage = 0;
    }

    public void setDelay(long d){
	delay = d;
    }

    public void update(){
	if (delay == -1)
	    return;

	long elapsed = System.currentTimeMillis() - startTime;
	if (elapsed > delay){
	    currentImage++;
	    startTime = System.currentTimeMillis();
	}
	//create loop
	if (currentImage >= frames.length)
	    currentImage = 0;
    }

    public BufferedImage getImage(){
	return frames[currentImage];
    }
}
