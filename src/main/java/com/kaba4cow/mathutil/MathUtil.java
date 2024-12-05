package com.kaba4cow.mathutil;

import com.kaba4cow.mathutil.vector.Vector2;
import com.kaba4cow.mathutil.vector.Vector3;

public class MathUtil {

	public static final float E = (float) Math.E;
	public static final float INV_E = 1.0f / E;

	public static final float PI = (float) Math.PI;
	public static final float TWO_PI = 2.0f * PI;
	public static final float HALF_PI = 0.5f * PI;
	public static final float QUARTER_PI = 0.25f * PI;

	public static final float INV_PI = 1.0f / PI;
	public static final float INV_TWO_PI = 1.0f / TWO_PI;
	public static final float INV_HALF_PI = 1.0f / HALF_PI;
	public static final float INV_QUARTER_PI = 1.0f / QUARTER_PI;

	public static final float SQRT2 = (float) Math.sqrt(2.0);
	public static final float CBRT3 = (float) Math.cbrt(3.0);

	private MathUtil() {}

	public static float interpolate(float a, float b, float t) {
		return a + (b - a) * t;
	}

	public static float clamp(float value, float min, float max) {
		return Math.min(Math.max(value, min), max);
	}

	public static float map(float value, float min1, float max1, float min2, float max2) {
		return min2 + (max2 - min2) * (value - min1) / (max1 - min1);
	}

	public static float normalize(float value, float min, float max) {
		return (value - min) / (max - min);
	}

	public static float square(float value) {
		return value * value;
	}

	public static float cube(float value) {
		return value * value * value;
	}

	public static Vector3 calculateNormal(Vector3 a, Vector3 b, Vector3 c) {
		return b.copy().sub(a).cross(c.copy().sub(a)).normalize();
	}

	public static Vector3 calculateBarycentric(Vector2 a, Vector2 b, Vector2 c, Vector2 p) {
		float determinant = 1.0f / ((a.x - c.x) * (b.y - c.y) - (b.x - c.x) * (a.y - c.y));
		float x = determinant * ((b.y - c.y) * (p.x - c.x) + (c.x - b.x) * (p.y - c.y));
		float y = determinant * ((c.y - a.y) * (p.x - c.x) + (a.x - c.x) * (p.y - c.y));
		return new Vector3().set(x, y, 1.0f - x - y);
	}

}
