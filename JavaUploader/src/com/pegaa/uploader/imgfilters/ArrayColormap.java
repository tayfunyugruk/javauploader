/*
** Copyright 2005 Huxtable.com. All rights reserved.
*/

package com.pegaa.uploader.imgfilters;

/**
 * A colormap implemented with an array of colors. This corresponds to the IndexColorModel class.
 */
public class ArrayColormap implements Colormap, Cloneable, java.io.Serializable {

	static final long serialVersionUID = -7990431442314209043L;
	
	/**
	 * The array of colors.
	 */
	protected int[] map;

	/**
	 * Construct an all-black colormap
	 */
	public ArrayColormap() {
		this.map = new int[256];
	}

	/**
	 * Construct a colormap with the given map
	 * @param map the array of ARGB colors
	 */
	public ArrayColormap(int[] map) {
		this.map = map;
	}

        @Override
	public Object clone() {
		try {
			ArrayColormap g = (ArrayColormap)super.clone();
			g.map = (int[])map.clone();
			return g;
		}
		catch (CloneNotSupportedException e) {
		}
		return null;
	}
	
	public void setMap(int[] map) {
		this.map = map;
	}

	public int[] getMap() {
		return map;
	}

	/**
	 * Convert a value in the range 0..1 to an RGB color.
	 * @param v a value in the range 0..1
	 * @return an RGB color
	 */
	public int getColor(float v) {
/*
		v *= 255;
		int n = (int)v;
		float f = v-n;
		if (n < 0)
			return map[0];
		else if (n >= 255)
			return map[255];
		return ImageMath.mixColors(f, map[n], map[n+1]);
*/
		int n = (int)(v*255);
		if (n < 0)
			n = 0;
		else if (n > 255)
			n = 255;
		return map[n];
	}
	
	/**
	 * Set the color at "index" to "color". Entries are interpolated linearly from
	 * the existing entries at "firstIndex" and "lastIndex" to the new entry.
	 * firstIndex < index < lastIndex must hold.
	 */
	public void setColorInterpolated(int index, int firstIndex, int lastIndex, int color) {
		int firstColor = map[firstIndex];
		int lastColor = map[lastIndex];
		for (int i = firstIndex; i <= index; i++)
			map[i] = ImageMath.mixColors((float)(i-firstIndex)/(index-firstIndex), firstColor, color);
		for (int i = index; i < lastIndex; i++)
			map[i] = ImageMath.mixColors((float)(i-index)/(lastIndex-index), color, lastColor);
	}

	public void setColorRange(int firstIndex, int lastIndex, int color1, int color2) {
		for (int i = firstIndex; i <= lastIndex; i++)
			map[i] = ImageMath.mixColors((float)(i-firstIndex)/(lastIndex-firstIndex), color1, color2);
	}

	public void setColorRange(int firstIndex, int lastIndex, int color) {
		for (int i = firstIndex; i <= lastIndex; i++)
			map[i] = color;
	}

	public void setColor(int index, int color) {
		map[index] = color;
	}

}
