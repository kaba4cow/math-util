package com.kaba4cow.mathutil.vector;

import java.util.Objects;

import com.kaba4cow.mathutil.matrix.Matrix4;

public class Vector4 implements Vector<Vector4> {

	public float x;
	public float y;
	public float z;
	public float w;

	public Vector4() {
		this.x = 0.0f;
		this.y = 0.0f;
		this.z = 0.0f;
		this.w = 0.0f;
	}

	public Vector4 transform(Matrix4 matrix) {
		return set(//
				matrix.m00 * this.x + matrix.m10 * this.y + matrix.m20 * this.z + matrix.m30 * this.w, //
				matrix.m01 * this.x + matrix.m11 * this.y + matrix.m21 * this.z + matrix.m31 * this.w, //
				matrix.m02 * this.x + matrix.m12 * this.y + matrix.m22 * this.z + matrix.m32 * this.w, //
				matrix.m03 * this.x + matrix.m13 * this.y + matrix.m23 * this.z + matrix.m33 * this.w);
	}

	@Override
	public Vector4 interpolate(Vector4 target, float t) {
		return set(//
				this.x + t * (target.x - this.x), //
				this.y + t * (target.y - this.y), //
				this.z + t * (target.z - this.z), //
				this.w + t * (target.w - this.w));
	}

	@Override
	public Vector4 add(Vector4 vector) {
		return set(//
				this.x + vector.x, //
				this.y + vector.y, //
				this.z + vector.z, //
				this.w + vector.w);
	}

	@Override
	public Vector4 sub(Vector4 vector) {
		return set(//
				this.x - vector.x, //
				this.y - vector.y, //
				this.z - vector.z, //
				this.w - vector.w);
	}

	@Override
	public Vector4 add(Vector4 vector, float scale) {
		return set(//
				this.x + scale * vector.x, //
				this.y + scale * vector.y, //
				this.z + scale * vector.z, //
				this.w + scale * vector.w);
	}

	@Override
	public Vector4 sub(Vector4 vector, float scale) {
		return set(//
				this.x - scale * vector.x, //
				this.y - scale * vector.y, //
				this.z - scale * vector.z, //
				this.w - scale * vector.w);
	}

	@Override
	public float lengthSquared() {
		return this.x * this.x + //
				this.y * this.y + //
				this.z * this.z + //
				this.w * this.w;
	}

	@Override
	public float distanceSquared(Vector4 vector) {
		return (this.x - vector.x) * (this.x - vector.x) + //
				(this.y - vector.y) * (this.y - vector.y) + //
				(this.z - vector.z) * (this.z - vector.z) + //
				(this.w - vector.w) * (this.w - vector.w);
	}

	@Override
	public Vector4 multiply(float value) {
		this.x *= value;
		this.y *= value;
		this.z *= value;
		this.w *= value;
		return this;
	}

	@Override
	public Vector4 copy() {
		return new Vector4().set(this);
	}

	@Override
	public Vector4 set(Vector4 vector) {
		return set(//
				vector.x, //
				vector.y, //
				vector.z, //
				vector.w);
	}

	@Override
	public float dot(Vector4 vector) {
		return this.x * vector.x + //
				this.y * vector.y + //
				this.z * vector.z + //
				this.w * vector.w;
	}

	@Override
	public float[] toArray() {
		return new float[] { //
				this.x, //
				this.y, //
				this.z, //
				this.w };
	}

	public Vector4 x(float x) {
		this.x = x;
		return this;
	}

	public Vector4 y(float y) {
		this.y = y;
		return this;
	}

	public Vector4 z(float z) {
		this.z = z;
		return this;
	}

	public Vector4 w(float w) {
		this.w = w;
		return this;
	}

	public Vector4 set(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(w, x, y, z);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector4 other = (Vector4) obj;
		return Float.floatToIntBits(w) == Float.floatToIntBits(other.w)
				&& Float.floatToIntBits(x) == Float.floatToIntBits(other.x)
				&& Float.floatToIntBits(y) == Float.floatToIntBits(other.y)
				&& Float.floatToIntBits(z) == Float.floatToIntBits(other.z);
	}

	@Override
	public String toString() {
		return String.format("Vector4f [%s, %s, %s, %s]", x, y, z, w);
	}

}
