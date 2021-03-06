package lab9;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.annotation.PostConstruct;

public class AreaCheck implements Serializable{
	double x;
	double xp;
	double y;
	double r;
	String res;

  private List<SelectItem> xvals;

  public List<SelectItem> getXvals() {
    return xvals;
  }

  @PostConstruct
  public void init() {
    xvals = new ArrayList<SelectItem>();
    xvals.add(new SelectItem(-5));
    xvals.add(new SelectItem(-4));
    xvals.add(new SelectItem(-3));
    xvals.add(new SelectItem(-2));
    xvals.add(new SelectItem(-1));
    xvals.add(new SelectItem(0));
    xvals.add(new SelectItem(1));
    xvals.add(new SelectItem(2));
    xvals.add(new SelectItem(3));
  }

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}
	
	public double getXp() {
		return xp;
	}

	public void setXp(double xp) {
		this.xp = xp;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	private static final ArrayList<Point> pointList =
		new ArrayList<Point>();

	public ArrayList<Point> getPointList() {
		return pointList;
	}

	public void addAction(double r) {
		double xx;
		if(r==0.0){
			this.r=1;
			xx=this.xp;
		}
		else{
			this.r = r;
			xx=this.x;
		}
		if(!pointList.isEmpty()){
			Point lastPoint = pointList.get(0);
			if(lastPoint.x == xx && lastPoint.y == this.y)
				return;
			if(r==0)
				this.r=lastPoint.r;
		}

		if(contains(xx,this.y,this.r))
			this.res = "IN";
		else
			this.res = "OUT";

		Point point = new Point(xx, this.y, this.r, this.res);
		pointList.add(0, point);
	}

	public void clearAction() {
		pointList.clear();
	}

	public boolean contains(double x, double y, double r)
	{
		//rectangle
		if (x >= 0 &&
			x <= r &&
			y <= 0 &&
			y >= -r/2)
		return true;

		//triangle
		if (x <= 0 &&
			y <= 0 &&
			y >= -x-r/2)
		return true;

		//circle
		if (x <= 0 &&
			y >= 0 &&
			(x*x + y*y) <= r*r/4)
		return true;

		return false;
	}
}
