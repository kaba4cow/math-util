package com.kaba4cow.mathutil.vector;

import java.util.Objects;

import com.kaba4cow.mathutil.matrix.Matrix3;

public class Vector3 implements Vector<Vector3> {

	public float x;
	public float y;
	public float z;

	public Vector3() {
		this.x = 0.0f;
		this.y = 0.0f;
		this.z = 0.0f;
	}

	public Vector3 transform(Matrix3 matrix) {
		return set(//
				matrix.m00 * this.x + matrix.m10 * this.y + matrix.m20 * this.z, //
				matrix.m01 * this.x + matrix.m11 * this.y + matrix.m21 * this.z, //
				matrix.m02 * this.x + matrix.m12 * this.y + matrix.m22 * this.z);
	}

	@Override
	public Vector3 interpolate(Vector3 target, float t) {
		return set(//
				this.x + t * (target.x - this.x), //
				this.y + t * (target.y - this.y), //
				this.z + t * (target.z - this.z));
	}

	public Vector3 cross(Vector3 vector) {
		return set(//
				this.y * vector.z - this.z * vector.y, //
				this.z * vector.x - this.x * vector.z, //
				this.x * vector.y - this.y * vector.x);
	}

	public Vector3 reflect(Vector3 normal) {
		float dot = 2.0f * normal.dot(this);
		return set(//
				this.x - dot * normal.x, //
				this.y - dot * normal.y, //
				this.z - dot * normal.z);
	}

	public Vector3 rotate(float angle, Vector3 axis) {
		float cos = (float) Math.cos(angle);
		float sin = (float) Math.sin(angle);
		float sum = (axis.x * this.x + axis.y * this.y + axis.z * this.z) * (1.0f - cos);
		return set(//
				axis.x * sum + this.x * cos + (-axis.z * this.y + axis.y * this.z) * sin, //
				axis.y * sum + this.y * cos + (axis.z * this.x - axis.x * this.z) * sin, //
				axis.z * sum + this.z * cos + (-axis.y * this.x + axis.x * this.y) * sin);
	}

	public Vector3 rotate(float angle, Vector3 axis, Vector3 origin) {
		float cos = (float) Math.cos(angle);
		float sin = (float) Math.sin(angle);
		float sum = (axis.x * this.x + axis.y * this.y + axis.z * this.z) * (1.0f - cos);
		float x = this.x - origin.x;
		float y = this.y - origin.y;
		float z = this.z - origin.z;
		return set(//
				origin.x + axis.x * sum + x * cos + (-axis.z * y + axis.y * z) * sin, //
				origin.y + axis.y * sum + y * cos + (axis.z * x - axis.x * z) * sin, //
				origin.z + axis.z * sum + z * cos + (-axis.y * x + axis.x * y) * sin);
	}

	@Override
	public Vector3 add(Vector3 vector) {
		return set(//
				this.x + vector.x, //
				this.y + vector.y, //
				this.z + vector.z);
	}

	@Override
	public Vector3 sub(Vector3 vector) {
		return set(//
				this.x - vector.x, //
				this.y - vector.y, //
				this.z - vector.z);
	}

	@Override
	public Vector3 add(Vector3 vector, float scale) {
		return set(//
				this.x + scale * vector.x, //
				this.y + scale * vector.y, //
				this.z + scale * vector.z);
	}

	@Override
	public Vector3 sub(Vector3 vector, float scale) {
		return set(//
				this.x - scale * vector.x, //
				this.y - scale * vector.y, //
				this.z - scale * vector.z);
	}

	@Override
	public float lengthSquared() {
		return this.x * this.x + //
				this.y * this.y + //
				this.z * this.z;
	}

	@Override
	public float distanceSquared(Vector3 vector) {
		return (this.x - vector.x) * (this.x - vector.x) + //
				(this.y - vector.y) * (this.y - vector.y) + //
				(this.z - vector.z) * (this.z - vector.z);
	}

	@Override
	public Vector3 multiply(float value) {
		this.x *= value;
		this.y *= value;
		this.z *= value;
		return this;
	}

	@Override
	public Vector3 copy() {
		return new Vector3().set(this);
	}

	@Override
	public Vector3 set(Vector3 vector) {
		return set(//
				vector.x, //
				vector.y, //
				vector.z);
	}

	@Override
	public float dot(Vector3 vector) {
		return this.x * vector.x + //
				this.y * vector.y + //
				this.z * vector.z;
	}

	@Override
	public float[] toArray() {
		return new float[] { //
				this.x, //
				this.y, //
				this.z };
	}

	public Vector3 x(float x) {
		this.x = x;
		return this;
	}

	public Vector3 y(float y) {
		this.y = y;
		return this;
	}

	public Vector3 z(float z) {
		this.z = z;
		return this;
	}

	public Vector3 set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector3 other = (Vector3) obj;
		return Float.floatToIntBits(x) == Float.floatToIntBits(other.x)
				&& Float.floatToIntBits(y) == Float.floatToIntBits(other.y)
				&& Float.floatToIntBits(z) == Float.floatToIntBits(other.z);
	}

	@Override
	public String toString() {
		return String.format("Vector3f [%s, %s, %s]", x, y, z);
	}

}
