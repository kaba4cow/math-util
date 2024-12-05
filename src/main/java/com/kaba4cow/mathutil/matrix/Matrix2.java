package com.kaba4cow.mathutil.matrix;

import java.util.Objects;

public class Matrix2 implements Matrix<Matrix2> {

	public float m00, m01;
	public float m10, m11;

	public Matrix2() {
		this.m00 = 0.0f;
		this.m01 = 0.0f;
		this.m10 = 0.0f;
		this.m11 = 0.0f;
	}

	@Override
	public Matrix2 identity() {
		return this//
				.row0(1.0f, 0.0f)//
				.row1(0.0f, 1.0f);
	}

	@Override
	public Matrix2 negate() {
		return this//
				.row0(-m00, -m01)//
				.row1(-m10, -m11);
	}

	@Override
	public Matrix2 transpose() {
		float m01 = this.m10;
		float m10 = this.m01;
		return this//
				.row0(m00, m01)//
				.row1(m10, m11);
	}

	@Override
	public Matrix2 multiply(Matrix2 matrix) {
		float m00 = this.m00 * matrix.m00 + this.m10 * matrix.m01;
		float m01 = this.m01 * matrix.m00 + this.m11 * matrix.m01;
		float m10 = this.m00 * matrix.m10 + this.m10 * matrix.m11;
		float m11 = this.m01 * matrix.m10 + this.m11 * matrix.m11;
		return this//
				.row0(m00, m01)//
				.row1(m10, m11);
	}

	@Override
	public Matrix2 invert() {
		float determinant = determinant();
		if (determinant != 0.0f) {
			float invDeterminant = 1.0f / determinant;
			float m00 = this.m11 * invDeterminant;
			float m01 = -this.m01 * invDeterminant;
			float m11 = this.m00 * invDeterminant;
			float m10 = -this.m10 * invDeterminant;
			return this//
					.row0(m00, m01)//
					.row1(m10, m11);
		}
		return this;
	}

	@Override
	public float determinant() {
		return m00 * m11 - m01 * m10;
	}

	@Override
	public Matrix2 copy() {
		return new Matrix2().set(this);
	}

	@Override
	public Matrix2 set(Matrix2 matrix) {
		return this//
				.row0(matrix.m00, matrix.m01)//
				.row1(matrix.m10, matrix.m11);
	}

	@Override
	public Matrix2 fromArray(float[] array) {
		int pointer = 0;
		return this//
				.row0(array[pointer++], array[pointer++])//
				.row1(array[pointer++], array[pointer++]);
	}

	@Override
	public float[] toArray() {
		return new float[] { //
				m00, m01, //
				m10, m11//
		};
	}

	public Matrix2 m00(float m00) {
		this.m00 = m00;
		return this;
	}

	public Matrix2 m01(float m01) {
		this.m01 = m01;
		return this;
	}

	public Matrix2 m10(float m10) {
		this.m10 = m10;
		return this;
	}

	public Matrix2 m11(float m11) {
		this.m11 = m11;
		return this;
	}

	public Matrix2 row0(float m00, float m01) {
		return m00(m00).m01(m01);
	}

	public Matrix2 row1(float m10, float m11) {
		return m10(m10).m11(m11);
	}

	public Matrix2 col0(float m00, float m10) {
		return m00(m00).m10(m10);
	}

	public Matrix2 col1(float m01, float m11) {
		return m01(m01).m11(m11);
	}

	@Override
	public int hashCode() {
		return Objects.hash(//
				m00, m01, //
				m10, m11);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matrix2 other = (Matrix2) obj;
		return Float.floatToIntBits(m00) == Float.floatToIntBits(other.m00)
				&& Float.floatToIntBits(m01) == Float.floatToIntBits(other.m01)
				&& Float.floatToIntBits(m10) == Float.floatToIntBits(other.m10)
				&& Float.floatToIntBits(m11) == Float.floatToIntBits(other.m11);
	}

	@Override
	public String toString() {
		return String.format("Matrix2f [%s, %s, %s, %s]", m00, m01, m10, m11);
	}

}
