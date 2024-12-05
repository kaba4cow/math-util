package com.kaba4cow.mathutil.matrix;

import java.util.Objects;

public class Matrix3 implements Matrix<Matrix3> {

	public float m00, m01, m02;
	public float m10, m11, m12;
	public float m20, m21, m22;

	public Matrix3() {
		this.m00 = 0.0f;
		this.m01 = 0.0f;
		this.m02 = 0.0f;
		this.m10 = 0.0f;
		this.m11 = 0.0f;
		this.m12 = 0.0f;
		this.m20 = 0.0f;
		this.m21 = 0.0f;
		this.m22 = 0.0f;
	}

	@Override
	public Matrix3 identity() {
		return this//
				.row0(1.0f, 0.0f, 0.0f)//
				.row1(0.0f, 1.0f, 0.0f)//
				.row2(0.0f, 0.0f, 1.0f);
	}

	@Override
	public Matrix3 negate() {
		return this//
				.row0(-m00, -m01, -m02)//
				.row1(-m10, -m11, -m12)//
				.row2(-m20, -m21, -m22);
	}

	@Override
	public Matrix3 transpose() {
		float m01 = this.m10;
		float m02 = this.m20;
		float m10 = this.m01;
		float m12 = this.m21;
		float m20 = this.m02;
		float m21 = this.m12;
		return this//
				.row0(m00, m01, m02)//
				.row1(m10, m11, m12)//
				.row2(m20, m21, m22);
	}

	@Override
	public Matrix3 multiply(Matrix3 matrix) {
		float m00 = this.m00 * matrix.m00 + this.m10 * matrix.m01 + this.m20 * matrix.m02;
		float m01 = this.m01 * matrix.m00 + this.m11 * matrix.m01 + this.m21 * matrix.m02;
		float m02 = this.m02 * matrix.m00 + this.m12 * matrix.m01 + this.m22 * matrix.m02;
		float m10 = this.m00 * matrix.m10 + this.m10 * matrix.m11 + this.m20 * matrix.m12;
		float m11 = this.m01 * matrix.m10 + this.m11 * matrix.m11 + this.m21 * matrix.m12;
		float m12 = this.m02 * matrix.m10 + this.m12 * matrix.m11 + this.m22 * matrix.m12;
		float m20 = this.m00 * matrix.m20 + this.m10 * matrix.m21 + this.m20 * matrix.m22;
		float m21 = this.m01 * matrix.m20 + this.m11 * matrix.m21 + this.m21 * matrix.m22;
		float m22 = this.m02 * matrix.m20 + this.m12 * matrix.m21 + this.m22 * matrix.m22;
		return this//
				.row0(m00, m01, m02)//
				.row1(m10, m11, m12)//
				.row2(m20, m21, m22);
	}

	@Override
	public Matrix3 invert() {
		float determinant = this.determinant();
		if (determinant != 0.0f) {
			float invDeterminant = 1.0f / determinant;
			float m00 = (this.m11 * this.m22 - this.m12 * this.m21) * invDeterminant;
			float m01 = (-this.m10 * this.m22 + this.m12 * this.m20) * invDeterminant;
			float m02 = (this.m10 * this.m21 - this.m11 * this.m20) * invDeterminant;
			float m10 = (-this.m01 * this.m22 + this.m02 * this.m21) * invDeterminant;
			float m11 = (this.m00 * this.m22 - this.m02 * this.m20) * invDeterminant;
			float m12 = (-this.m00 * this.m21 + this.m01 * this.m20) * invDeterminant;
			float m20 = (this.m01 * this.m12 - this.m02 * this.m11) * invDeterminant;
			float m21 = (-this.m00 * this.m12 + this.m02 * this.m10) * invDeterminant;
			float m22 = (this.m00 * this.m11 - this.m01 * this.m10) * invDeterminant;
			return this//
					.row0(m00, m01, m02)//
					.row1(m10, m11, m12)//
					.row2(m20, m21, m22);
		}
		return this;
	}

	@Override
	public float determinant() {
		return m00 * (m11 * m22 - m12 * m21) + //
				m01 * (m12 * m20 - m10 * m22) + //
				m02 * (m10 * m21 - m11 * m20);
	}

	@Override
	public Matrix3 copy() {
		return new Matrix3().set(this);
	}

	@Override
	public Matrix3 set(Matrix3 matrix) {
		return this//
				.row0(matrix.m00, matrix.m01, matrix.m02)//
				.row1(matrix.m10, matrix.m11, matrix.m12)//
				.row2(matrix.m20, matrix.m21, matrix.m22);
	}

	@Override
	public Matrix3 fromArray(float[] array) {
		int pointer = 0;
		return this//
				.row0(array[pointer++], array[pointer++], array[pointer++])//
				.row1(array[pointer++], array[pointer++], array[pointer++])//
				.row2(array[pointer++], array[pointer++], array[pointer++]);
	}

	@Override
	public float[] toArray() {
		return new float[] { //
				m00, m01, m02, //
				m10, m11, m12, //
				m20, m21, m22//
		};
	}

	public Matrix3 m00(float m00) {
		this.m00 = m00;
		return this;
	}

	public Matrix3 m01(float m01) {
		this.m01 = m01;
		return this;
	}

	public Matrix3 m02(float m02) {
		this.m02 = m02;
		return this;
	}

	public Matrix3 m10(float m10) {
		this.m10 = m10;
		return this;
	}

	public Matrix3 m11(float m11) {
		this.m11 = m11;
		return this;
	}

	public Matrix3 m12(float m12) {
		this.m12 = m12;
		return this;
	}

	public Matrix3 m20(float m20) {
		this.m20 = m20;
		return this;
	}

	public Matrix3 m21(float m21) {
		this.m21 = m21;
		return this;
	}

	public Matrix3 m22(float m22) {
		this.m22 = m22;
		return this;
	}

	public Matrix3 row0(float m00, float m01, float m02) {
		return m00(m00).m01(m01).m02(m02);
	}

	public Matrix3 row1(float m10, float m11, float m12) {
		return m10(m10).m11(m11).m12(m12);
	}

	public Matrix3 row2(float m20, float m21, float m22) {
		return m20(m20).m21(m21).m22(m22);
	}

	public Matrix3 col0(float m00, float m10, float m20) {
		return m00(m00).m10(m10).m20(m20);
	}

	public Matrix3 col1(float m01, float m11, float m21) {
		return m01(m01).m11(m11).m21(m21);
	}

	public Matrix3 col2(float m02, float m12, float m22) {
		return m02(m02).m12(m12).m22(m22);
	}

	@Override
	public int hashCode() {
		return Objects.hash(//
				m00, m01, m02, //
				m10, m11, m12, //
				m20, m21, m22);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matrix3 other = (Matrix3) obj;
		return Float.floatToIntBits(m00) == Float.floatToIntBits(other.m00)
				&& Float.floatToIntBits(m01) == Float.floatToIntBits(other.m01)
				&& Float.floatToIntBits(m02) == Float.floatToIntBits(other.m02)
				&& Float.floatToIntBits(m10) == Float.floatToIntBits(other.m10)
				&& Float.floatToIntBits(m11) == Float.floatToIntBits(other.m11)
				&& Float.floatToIntBits(m12) == Float.floatToIntBits(other.m12)
				&& Float.floatToIntBits(m20) == Float.floatToIntBits(other.m20)
				&& Float.floatToIntBits(m21) == Float.floatToIntBits(other.m21)
				&& Float.floatToIntBits(m22) == Float.floatToIntBits(other.m22);
	}

	@Override
	public String toString() {
		return String.format("Matrix3f [%s, %s, %s, %s, %s, %s, %s, %s, %s]", m00, m01, m02, m10, m11, m12, m20, m21, m22);
	}

}
