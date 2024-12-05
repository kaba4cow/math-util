package com.kaba4cow.mathutil.intvector;

import java.util.Objects;

public class IntVector4 implements IntVector<IntVector4> {

	public int x;
	public int y;
	public int z;
	public int w;

	public IntVector4() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.w = 0;
	}

	@Override
	public IntVector4 add(IntVector4 vector) {
		return set(//
				this.x + vector.x, //
				this.y + vector.y, //
				this.z + vector.z, //
				this.w + vector.w);
	}

	@Override
	public IntVector4 sub(IntVector4 vector) {
		return set(//
				this.x - vector.x, //
				this.y - vector.y, //
				this.z - vector.z, //
				this.w - vector.w);
	}

	@Override
	public IntVector4 add(IntVector4 vector, int scale) {
		return set(//
				this.x + scale * vector.x, //
				this.y + scale * vector.y, //
				this.z + scale * vector.z, //
				this.w + scale * vector.w);
	}

	@Override
	public IntVector4 sub(IntVector4 vector, int scale) {
		return set(//
				this.x - scale * vector.x, //
				this.y - scale * vector.y, //
				this.z - scale * vector.z, //
				this.w - scale * vector.w);
	}

	@Override
	public int length() {
		return Math.abs(x) + //
				Math.abs(y) + //
				Math.abs(z) + //
				Math.abs(w);
	}

	@Override
	public int distance(IntVector4 vector) {
		return Math.abs(vector.x - this.x) + //
				Math.abs(vector.y - this.y) + //
				Math.abs(vector.z - this.z) + //
				Math.abs(vector.w - this.w);
	}

	@Override
	public IntVector4 multiply(int value) {
		this.x *= value;
		this.y *= value;
		this.z *= value;
		this.w *= value;
		return this;
	}

	@Override
	public IntVector4 divide(int value) {
		this.x /= value;
		this.y /= value;
		this.z /= value;
		this.w /= value;
		return this;
	}

	@Override
	public IntVector4 copy() {
		return new IntVector4().set(this);
	}

	@Override
	public IntVector4 set(IntVector4 vector) {
		return set(//
				vector.x, //
				vector.y, //
				vector.z, //
				vector.w);
	}

	@Override
	public int dot(IntVector4 vector) {
		return this.x * vector.x + //
				this.y * vector.y + //
				this.z * vector.z + //
				this.w * vector.w;
	}

	@Override
	public int[] toArray() {
		return new int[] { //
				this.x, //
				this.y, //
				this.z, //
				this.w };
	}

	public IntVector4 x(int x) {
		this.x = x;
		return this;
	}

	public IntVector4 y(int y) {
		this.y = y;
		return this;
	}

	public IntVector4 z(int z) {
		this.z = z;
		return this;
	}

	public IntVector4 w(int w) {
		this.w = w;
		return this;
	}

	public IntVector4 set(int x, int y, int z, int w) {
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
		IntVector4 other = (IntVector4) obj;
		return w == other.w && x == other.x && y == other.y && z == other.z;
	}

	@Override
	public String toString() {
		return String.format("Vector4i [%s, %s, %s, %s]", x, y, z, w);
	}

}
