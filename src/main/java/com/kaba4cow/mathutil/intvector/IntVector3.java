package com.kaba4cow.mathutil.intvector;

import java.util.Objects;

public class IntVector3 implements IntVector<IntVector3> {

	public int x;
	public int y;
	public int z;

	public IntVector3() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

	@Override
	public IntVector3 add(IntVector3 vector) {
		return set(//
				this.x + vector.x, //
				this.y + vector.y, //
				this.z + vector.z);
	}

	@Override
	public IntVector3 sub(IntVector3 vector) {
		return set(//
				this.x - vector.x, //
				this.y - vector.y, //
				this.z - vector.z);
	}

	@Override
	public IntVector3 add(IntVector3 vector, int scale) {
		return set(//
				this.x + scale * vector.x, //
				this.y + scale * vector.y, //
				this.z + scale * vector.z);
	}

	@Override
	public IntVector3 sub(IntVector3 vector, int scale) {
		return set(//
				this.x - scale * vector.x, //
				this.y - scale * vector.y, //
				this.z - scale * vector.z);
	}

	@Override
	public int length() {
		return Math.abs(x) + //
				Math.abs(y) + //
				Math.abs(z);
	}

	@Override
	public int distance(IntVector3 vector) {
		return Math.abs(vector.x - this.x) + //
				Math.abs(vector.y - this.y) + //
				Math.abs(vector.z - this.z);
	}

	@Override
	public IntVector3 multiply(int value) {
		this.x *= value;
		this.y *= value;
		this.z *= value;
		return this;
	}

	@Override
	public IntVector3 divide(int value) {
		this.x /= value;
		this.y /= value;
		this.z /= value;
		return this;
	}

	@Override
	public IntVector3 copy() {
		return new IntVector3().set(this);
	}

	@Override
	public IntVector3 set(IntVector3 vector) {
		return set(//
				vector.x, //
				vector.y, //
				vector.z);
	}

	@Override
	public int dot(IntVector3 vector) {
		return this.x * vector.x + //
				this.y * vector.y + //
				this.z * vector.z;
	}

	@Override
	public int[] toArray() {
		return new int[] { //
				this.x, //
				this.y, //
				this.z };
	}

	public IntVector3 x(int x) {
		this.x = x;
		return this;
	}

	public IntVector3 y(int y) {
		this.y = y;
		return this;
	}

	public IntVector3 z(int z) {
		this.z = z;
		return this;
	}

	public IntVector3 set(int x, int y, int z) {
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
		IntVector3 other = (IntVector3) obj;
		return x == other.x && y == other.y && z == other.z;
	}

	@Override
	public String toString() {
		return String.format("Vector3i [%s, %s, %s]", x, y, z);
	}

}
