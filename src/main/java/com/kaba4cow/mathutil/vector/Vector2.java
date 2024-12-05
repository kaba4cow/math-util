package com.kaba4cow.mathutil.vector;

import java.util.Objects;

import com.kaba4cow.mathutil.matrix.Matrix2;

public class Vector2 implements Vector<Vector2> {

	public float x;
	public float y;

	public Vector2() {
		this.x = 0.0f;
		this.y = 0.0f;
	}

	public Vector2 transform(Matrix2 matrix) {
		return set(//
				matrix.m00 * this.x + matrix.m10 * this.y, //
				matrix.m01 * this.x + matrix.m11 * this.y);
	}

	@Override
	public Vector2 interpolate(Vector2 target, float t) {
		return set(//
				this.x + t * (target.x - this.x), //
				this.y + t * (target.y - this.y));
	}

	public Vector2 reflect(Vector2 normal) {
		float dot = 2.0f * normal.dot(this);
		return set(//
				this.x - dot * normal.x, //
				this.y - dot * normal.y);
	}

	public Vector2 rotate(float angle) {
		float cos = (float) Math.cos(angle);
		float sin = (float) Math.sin(angle);
		return set(//
				this.x * cos - this.y * sin, //
				this.x * sin + this.y * cos);
	}

	public Vector2 rotate(float angle, Vector2 origin) {
		float cos = (float) Math.cos(angle);
		float sin = (float) Math.sin(angle);
		float x = this.x - origin.x;
		float y = this.y - origin.y;
		return set(//
				origin.x + x * cos - y * sin, //
				origin.y + x * sin + y * cos);
	}

	@Override
	public Vector2 add(Vector2 vector) {
		return set(//
				this.x + vector.x, //
				this.y + vector.y);
	}

	@Override
	public Vector2 sub(Vector2 vector) {
		return set(//
				this.x - vector.x, //
				this.y - vector.y);
	}

	@Override
	public Vector2 add(Vector2 vector, float scale) {
		return set(//
				this.x + scale * vector.x, //
				this.y + scale * vector.y);
	}

	@Override
	public Vector2 sub(Vector2 vector, float scale) {
		return set(//
				this.x - scale * vector.x, //
				this.y - scale * vector.y);
	}

	@Override
	public float lengthSquared() {
		return this.x * this.x + //
				this.y * this.y;
	}

	@Override
	public float distanceSquared(Vector2 vector) {
		return (this.x - vector.x) * (this.x - vector.x) + //
				(this.y - vector.y) * (this.y - vector.y);
	}

	@Override
	public Vector2 multiply(float value) {
		this.x *= value;
		this.y *= value;
		return this;
	}

	@Override
	public Vector2 copy() {
		return new Vector2().set(this);
	}

	@Override
	public Vector2 set(Vector2 vector) {
		return set(//
				vector.x, //
				vector.y);
	}

	@Override
	public float dot(Vector2 vector) {
		return this.x * vector.x + //
				this.y * vector.y;
	}

	@Override
	public float[] toArray() {
		return new float[] { //
				this.x, //
				this.y };
	}

	public Vector2 x(float x) {
		this.x = x;
		return this;
	}

	public Vector2 y(float y) {
		this.y = y;
		return this;
	}

	public Vector2 set(float x, float y) {
		this.x = x;
		this.y = y;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector2 other = (Vector2) obj;
		return Float.floatToIntBits(x) == Float.floatToIntBits(other.x)
				&& Float.floatToIntBits(y) == Float.floatToIntBits(other.y);
	}

	@Override
	public String toString() {
		return String.format("Vector2f [%s, %s]", x, y);
	}

}
