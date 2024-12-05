package com.kaba4cow.mathutil.intvector;

import java.util.Objects;

public class IntVector2 implements IntVector<IntVector2> {

	public int x;
	public int y;

	public IntVector2() {
		this.x = 0;
		this.y = 0;
	}

	@Override
	public IntVector2 add(IntVector2 vector) {
		return set(//
				this.x + vector.x, //
				this.y + vector.y);
	}

	@Override
	public IntVector2 sub(IntVector2 vector) {
		return set(//
				this.x - vector.x, //
				this.y - vector.y);
	}

	@Override
	public IntVector2 add(IntVector2 vector, int scale) {
		return set(//
				this.x + scale * vector.x, //
				this.y + scale * vector.y);
	}

	@Override
	public IntVector2 sub(IntVector2 vector, int scale) {
		return set(//
				this.x - scale * vector.x, //
				this.y - scale * vector.y);
	}

	@Override
	public int length() {
		return Math.abs(x) + //
				Math.abs(y);
	}

	@Override
	public int distance(IntVector2 vector) {
		return Math.abs(vector.x - this.x) + //
				Math.abs(vector.y - this.y);
	}

	@Override
	public IntVector2 multiply(int value) {
		this.x *= value;
		this.y *= value;
		return this;
	}

	@Override
	public IntVector2 divide(int value) {
		this.x /= value;
		this.y /= value;
		return this;
	}

	@Override
	public IntVector2 copy() {
		return new IntVector2().set(this);
	}

	@Override
	public IntVector2 set(IntVector2 vector) {
		return set(//
				vector.x, //
				vector.y);
	}

	@Override
	public int dot(IntVector2 vector) {
		return this.x * vector.x + //
				this.y * vector.y;
	}

	@Override
	public int[] toArray() {
		return new int[] { //
				this.x, //
				this.y };
	}

	public IntVector2 x(int x) {
		this.x = x;
		return this;
	}

	public IntVector2 y(int y) {
		this.y = y;
		return this;
	}

	public IntVector2 set(int x, int y) {
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
		IntVector2 other = (IntVector2) obj;
		return x == other.x && y == other.y;
	}

	@Override
	public String toString() {
		return String.format("Vector2i [%s, %s]", x, y);
	}

}
