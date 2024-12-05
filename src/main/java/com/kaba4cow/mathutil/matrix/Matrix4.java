package com.kaba4cow.mathutil.matrix;

import java.util.Objects;

import com.kaba4cow.mathutil.vector.Vector3;

public class Matrix4 implements Matrix<Matrix4> {

	public float m00, m01, m02, m03;
	public float m10, m11, m12, m13;
	public float m20, m21, m22, m23;
	public float m30, m31, m32, m33;

	public Matrix4() {
		this.m00 = 0.0f;
		this.m01 = 0.0f;
		this.m02 = 0.0f;
		this.m03 = 0.0f;
		this.m10 = 0.0f;
		this.m11 = 0.0f;
		this.m12 = 0.0f;
		this.m13 = 0.0f;
		this.m20 = 0.0f;
		this.m21 = 0.0f;
		this.m22 = 0.0f;
		this.m23 = 0.0f;
		this.m30 = 0.0f;
		this.m31 = 0.0f;
		this.m32 = 0.0f;
		this.m33 = 0.0f;
	}

	public Matrix4 translate(Vector3 translation) {
		this.m30 += this.m00 * translation.x + this.m10 * translation.y + this.m20 * translation.z;
		this.m31 += this.m01 * translation.x + this.m11 * translation.y + this.m21 * translation.z;
		this.m32 += this.m02 * translation.x + this.m12 * translation.y + this.m22 * translation.z;
		this.m33 += this.m03 * translation.x + this.m13 * translation.y + this.m23 * translation.z;
		return this;
	}

	public Matrix4 scale(Vector3 scale) {
		this.m00 *= scale.x;
		this.m01 *= scale.x;
		this.m02 *= scale.x;
		this.m03 *= scale.x;
		this.m10 *= scale.y;
		this.m11 *= scale.y;
		this.m12 *= scale.y;
		this.m13 *= scale.y;
		this.m20 *= scale.z;
		this.m21 *= scale.z;
		this.m22 *= scale.z;
		this.m23 *= scale.z;
		return this;
	}

	public Matrix4 scale(float scale) {
		this.m00 *= scale;
		this.m01 *= scale;
		this.m02 *= scale;
		this.m03 *= scale;
		this.m10 *= scale;
		this.m11 *= scale;
		this.m12 *= scale;
		this.m13 *= scale;
		this.m20 *= scale;
		this.m21 *= scale;
		this.m22 *= scale;
		this.m23 *= scale;
		return this;
	}

	public Matrix4 rotate(Vector3 axis, float angle) {
		float cos = (float) Math.cos(angle);
		float sin = (float) Math.sin(angle);
		float cos2 = 1.0f - cos;
		float f00 = axis.x * axis.x * cos2 + cos;
		float f01 = axis.x * axis.y * cos2 + axis.z * sin;
		float f02 = axis.x * axis.z * cos2 - axis.y * sin;
		float f10 = axis.x * axis.y * cos2 - axis.z * sin;
		float f11 = axis.y * axis.y * cos2 + cos;
		float f12 = axis.y * axis.z * cos2 + axis.x * sin;
		float f20 = axis.x * axis.z * cos2 + axis.y * sin;
		float f21 = axis.y * axis.z * cos2 - axis.x * sin;
		float f22 = axis.z * axis.z * cos2 + cos;
		float t00 = this.m00 * f00 + this.m10 * f01 + this.m20 * f02;
		float t01 = this.m01 * f00 + this.m11 * f01 + this.m21 * f02;
		float t02 = this.m02 * f00 + this.m12 * f01 + this.m22 * f02;
		float t03 = this.m03 * f00 + this.m13 * f01 + this.m23 * f02;
		float t10 = this.m00 * f10 + this.m10 * f11 + this.m20 * f12;
		float t11 = this.m01 * f10 + this.m11 * f11 + this.m21 * f12;
		float t12 = this.m02 * f10 + this.m12 * f11 + this.m22 * f12;
		float t13 = this.m03 * f10 + this.m13 * f11 + this.m23 * f12;
		return this//
				.m20(this.m00 * f20 + this.m10 * f21 + this.m20 * f22)//
				.m21(this.m01 * f20 + this.m11 * f21 + this.m21 * f22)//
				.m22(this.m02 * f20 + this.m12 * f21 + this.m22 * f22)//
				.m23(this.m03 * f20 + this.m13 * f21 + this.m23 * f22)//
				.row0(t00, t01, t02, t03)//
				.row1(t10, t11, t12, t13);
	}

	public Matrix4 clearTranslation() {
		return m30(0.0f).m31(0.0f).m32(0.0f);
	}

	public Matrix4 clearRotation() {
		return this//
				.row0(1.0f, 0.0f, 0.0f, m03)//
				.row1(0.0f, 1.0f, 0.0f, m13)//
				.row2(0.0f, 0.0f, 1.0f, m23);
	}

	@Override
	public Matrix4 identity() {
		return this//
				.row0(1.0f, 0.0f, 0.0f, 0.0f)//
				.row1(0.0f, 1.0f, 0.0f, 0.0f)//
				.row2(0.0f, 0.0f, 1.0f, 0.0f)//
				.row3(0.0f, 0.0f, 0.0f, 1.0f);
	}

	@Override
	public Matrix4 negate() {
		return this//
				.row0(-m00, -m01, -m02, -m03)//
				.row1(-m10, -m11, -m12, -m13)//
				.row2(-m20, -m21, -m22, -m23)//
				.row3(-m30, -m31, -m32, -m33);
	}

	@Override
	public Matrix4 transpose() {
		float m01 = this.m10;
		float m02 = this.m20;
		float m03 = this.m30;
		float m10 = this.m01;
		float m12 = this.m21;
		float m13 = this.m31;
		float m20 = this.m02;
		float m21 = this.m12;
		float m23 = this.m32;
		float m30 = this.m03;
		float m31 = this.m13;
		float m32 = this.m23;
		return this//
				.row0(m00, m01, m02, m03)//
				.row1(m10, m11, m12, m13)//
				.row2(m20, m21, m22, m23)//
				.row3(m30, m31, m32, m33);
	}

	@Override
	public Matrix4 multiply(Matrix4 matrix) {
		float m00 = this.m00 * matrix.m00 + this.m10 * matrix.m01 + this.m20 * matrix.m02 + this.m30 * matrix.m03;
		float m01 = this.m01 * matrix.m00 + this.m11 * matrix.m01 + this.m21 * matrix.m02 + this.m31 * matrix.m03;
		float m02 = this.m02 * matrix.m00 + this.m12 * matrix.m01 + this.m22 * matrix.m02 + this.m32 * matrix.m03;
		float m03 = this.m03 * matrix.m00 + this.m13 * matrix.m01 + this.m23 * matrix.m02 + this.m33 * matrix.m03;
		float m10 = this.m00 * matrix.m10 + this.m10 * matrix.m11 + this.m20 * matrix.m12 + this.m30 * matrix.m13;
		float m11 = this.m01 * matrix.m10 + this.m11 * matrix.m11 + this.m21 * matrix.m12 + this.m31 * matrix.m13;
		float m12 = this.m02 * matrix.m10 + this.m12 * matrix.m11 + this.m22 * matrix.m12 + this.m32 * matrix.m13;
		float m13 = this.m03 * matrix.m10 + this.m13 * matrix.m11 + this.m23 * matrix.m12 + this.m33 * matrix.m13;
		float m20 = this.m00 * matrix.m20 + this.m10 * matrix.m21 + this.m20 * matrix.m22 + this.m30 * matrix.m23;
		float m21 = this.m01 * matrix.m20 + this.m11 * matrix.m21 + this.m21 * matrix.m22 + this.m31 * matrix.m23;
		float m22 = this.m02 * matrix.m20 + this.m12 * matrix.m21 + this.m22 * matrix.m22 + this.m32 * matrix.m23;
		float m23 = this.m03 * matrix.m20 + this.m13 * matrix.m21 + this.m23 * matrix.m22 + this.m33 * matrix.m23;
		float m30 = this.m00 * matrix.m30 + this.m10 * matrix.m31 + this.m20 * matrix.m32 + this.m30 * matrix.m33;
		float m31 = this.m01 * matrix.m30 + this.m11 * matrix.m31 + this.m21 * matrix.m32 + this.m31 * matrix.m33;
		float m32 = this.m02 * matrix.m30 + this.m12 * matrix.m31 + this.m22 * matrix.m32 + this.m32 * matrix.m33;
		float m33 = this.m03 * matrix.m30 + this.m13 * matrix.m31 + this.m23 * matrix.m32 + this.m33 * matrix.m33;
		return this//
				.row0(m00, m01, m02, m03)//
				.row1(m10, m11, m12, m13)//
				.row2(m20, m21, m22, m23)//
				.row3(m30, m31, m32, m33);
	}

	@Override
	public Matrix4 invert() {
		float determinant = determinant();
		if (determinant != 0.0f) {
			float invDeterminant = 1.0f / determinant;
			float m00 = determinant(this.m11, this.m12, this.m13, this.m21, this.m22, this.m23, this.m31, this.m32, this.m33)
					* invDeterminant;
			float m01 = -determinant(this.m10, this.m12, this.m13, this.m20, this.m22, this.m23, this.m30, this.m32, this.m33)
					* invDeterminant;
			float m02 = determinant(this.m10, this.m11, this.m13, this.m20, this.m21, this.m23, this.m30, this.m31, this.m33)
					* invDeterminant;
			float m03 = -determinant(this.m10, this.m11, this.m12, this.m20, this.m21, this.m22, this.m30, this.m31, this.m32)
					* invDeterminant;
			float m10 = -determinant(this.m01, this.m02, this.m03, this.m21, this.m22, this.m23, this.m31, this.m32, this.m33)
					* invDeterminant;
			float m11 = determinant(this.m00, this.m02, this.m03, this.m20, this.m22, this.m23, this.m30, this.m32, this.m33)
					* invDeterminant;
			float m12 = -determinant(this.m00, this.m01, this.m03, this.m20, this.m21, this.m23, this.m30, this.m31, this.m33)
					* invDeterminant;
			float m13 = determinant(this.m00, this.m01, this.m02, this.m20, this.m21, this.m22, this.m30, this.m31, this.m32)
					* invDeterminant;
			float m20 = determinant(this.m01, this.m02, this.m03, this.m11, this.m12, this.m13, this.m31, this.m32, this.m33)
					* invDeterminant;
			float m21 = -determinant(this.m00, this.m02, this.m03, this.m10, this.m12, this.m13, this.m30, this.m32, this.m33)
					* invDeterminant;
			float m22 = determinant(this.m00, this.m01, this.m03, this.m10, this.m11, this.m13, this.m30, this.m31, this.m33)
					* invDeterminant;
			float m23 = -determinant(this.m00, this.m01, this.m02, this.m10, this.m11, this.m12, this.m30, this.m31, this.m32)
					* invDeterminant;
			float m30 = -determinant(this.m01, this.m02, this.m03, this.m11, this.m12, this.m13, this.m21, this.m22, this.m23)
					* invDeterminant;
			float m31 = determinant(this.m00, this.m02, this.m03, this.m10, this.m12, this.m13, this.m20, this.m22, this.m23)
					* invDeterminant;
			float m32 = -determinant(this.m00, this.m01, this.m03, this.m10, this.m11, this.m13, this.m20, this.m21, this.m23)
					* invDeterminant;
			float m33 = determinant(this.m00, this.m01, this.m02, this.m10, this.m11, this.m12, this.m20, this.m21, this.m22)
					* invDeterminant;
			return this//
					.row0(m00, m01, m02, m03)//
					.row1(m10, m11, m12, m13)//
					.row2(m20, m21, m22, m23)//
					.row3(m30, m31, m32, m33);
		}
		return this;
	}

	private float determinant(//
			float m00, float m01, float m02, //
			float m10, float m11, float m12, //
			float m20, float m21, float m22) {
		return m00 * (m11 * m22 - m12 * m21) + m01 * (m12 * m20 - m10 * m22) + m02 * (m10 * m21 - m11 * m20);
	}

	@Override
	public float determinant() {
		float f = 0.0f;
		f += m00 * ((m11 * m22 * m33 + m12 * m23 * m31 + m13 * m21 * m32) - //
				m13 * m22 * m31 - m11 * m23 * m32 - m12 * m21 * m33);
		f -= m01 * ((m10 * m22 * m33 + m12 * m23 * m30 + m13 * m20 * m32) - //
				m13 * m22 * m30 - m10 * m23 * m32 - m12 * m20 * m33);
		f += m02 * ((m10 * m21 * m33 + m11 * m23 * m30 + m13 * m20 * m31) - //
				m13 * m21 * m30 - m10 * m23 * m31 - m11 * m20 * m33);
		f -= m03 * ((m10 * m21 * m32 + m11 * m22 * m30 + m12 * m20 * m31) - //
				m12 * m21 * m30 - m10 * m22 * m31 - m11 * m20 * m32);
		return f;
	}

	@Override
	public Matrix4 copy() {
		return new Matrix4().set(this);
	}

	@Override
	public Matrix4 set(Matrix4 matrix) {
		return this//
				.row0(matrix.m00, matrix.m01, matrix.m02, matrix.m03)//
				.row1(matrix.m10, matrix.m11, matrix.m12, matrix.m13)//
				.row2(matrix.m20, matrix.m21, matrix.m22, matrix.m23)//
				.row3(matrix.m30, matrix.m31, matrix.m32, matrix.m33);
	}

	@Override
	public Matrix4 fromArray(float[] array) {
		int pointer = 0;
		return this//
				.row0(array[pointer++], array[pointer++], array[pointer++], array[pointer++])//
				.row1(array[pointer++], array[pointer++], array[pointer++], array[pointer++])//
				.row2(array[pointer++], array[pointer++], array[pointer++], array[pointer++])//
				.row3(array[pointer++], array[pointer++], array[pointer++], array[pointer++]);
	}

	@Override
	public float[] toArray() {
		return new float[] { //
				m00, m01, m02, m03, //
				m10, m11, m12, m13, //
				m20, m21, m22, m23, //
				m30, m31, m32, m33//
		};
	}

	public Matrix4 m00(float m00) {
		this.m00 = m00;
		return this;
	}

	public Matrix4 m01(float m01) {
		this.m01 = m01;
		return this;
	}

	public Matrix4 m02(float m02) {
		this.m02 = m02;
		return this;
	}

	public Matrix4 m03(float m03) {
		this.m03 = m03;
		return this;
	}

	public Matrix4 m10(float m10) {
		this.m10 = m10;
		return this;
	}

	public Matrix4 m11(float m11) {
		this.m11 = m11;
		return this;
	}

	public Matrix4 m12(float m12) {
		this.m12 = m12;
		return this;
	}

	public Matrix4 m13(float m13) {
		this.m13 = m13;
		return this;
	}

	public Matrix4 m20(float m20) {
		this.m20 = m20;
		return this;
	}

	public Matrix4 m21(float m21) {
		this.m21 = m21;
		return this;
	}

	public Matrix4 m22(float m22) {
		this.m22 = m22;
		return this;
	}

	public Matrix4 m23(float m23) {
		this.m23 = m23;
		return this;
	}

	public Matrix4 m30(float m30) {
		this.m30 = m30;
		return this;
	}

	public Matrix4 m31(float m31) {
		this.m31 = m31;
		return this;
	}

	public Matrix4 m32(float m32) {
		this.m32 = m32;
		return this;
	}

	public Matrix4 m33(float m33) {
		this.m33 = m33;
		return this;
	}

	public Matrix4 row0(float m00, float m01, float m02, float m03) {
		return m00(m00).m01(m01).m02(m02).m03(m03);
	}

	public Matrix4 row1(float m10, float m11, float m12, float m13) {
		return m10(m10).m11(m11).m12(m12).m13(m13);
	}

	public Matrix4 row2(float m20, float m21, float m22, float m23) {
		return m20(m20).m21(m21).m22(m22).m23(m23);
	}

	public Matrix4 row3(float m30, float m31, float m32, float m33) {
		return m30(m30).m31(m31).m32(m32).m33(m33);
	}

	public Matrix4 col0(float m00, float m10, float m20, float m30) {
		return m00(m00).m10(m10).m20(m20).m30(m30);
	}

	public Matrix4 col1(float m01, float m11, float m21, float m31) {
		return m01(m01).m11(m11).m21(m21).m31(m31);
	}

	public Matrix4 col2(float m02, float m12, float m22, float m32) {
		return m02(m02).m12(m12).m22(m22).m32(m32);
	}

	public Matrix4 col3(float m03, float m13, float m23, float m33) {
		return m03(m03).m13(m13).m23(m23).m33(m33);
	}

	@Override
	public int hashCode() {
		return Objects.hash(//
				m00, m01, m02, m03, //
				m10, m11, m12, m13, //
				m20, m21, m22, m23, //
				m30, m31, m32, m33);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matrix4 other = (Matrix4) obj;
		return Float.floatToIntBits(m00) == Float.floatToIntBits(other.m00)
				&& Float.floatToIntBits(m01) == Float.floatToIntBits(other.m01)
				&& Float.floatToIntBits(m02) == Float.floatToIntBits(other.m02)
				&& Float.floatToIntBits(m03) == Float.floatToIntBits(other.m03)
				&& Float.floatToIntBits(m10) == Float.floatToIntBits(other.m10)
				&& Float.floatToIntBits(m11) == Float.floatToIntBits(other.m11)
				&& Float.floatToIntBits(m12) == Float.floatToIntBits(other.m12)
				&& Float.floatToIntBits(m13) == Float.floatToIntBits(other.m13)
				&& Float.floatToIntBits(m20) == Float.floatToIntBits(other.m20)
				&& Float.floatToIntBits(m21) == Float.floatToIntBits(other.m21)
				&& Float.floatToIntBits(m22) == Float.floatToIntBits(other.m22)
				&& Float.floatToIntBits(m23) == Float.floatToIntBits(other.m23)
				&& Float.floatToIntBits(m30) == Float.floatToIntBits(other.m30)
				&& Float.floatToIntBits(m31) == Float.floatToIntBits(other.m31)
				&& Float.floatToIntBits(m32) == Float.floatToIntBits(other.m32)
				&& Float.floatToIntBits(m33) == Float.floatToIntBits(other.m33);
	}

	@Override
	public String toString() {
		return String.format("Matrix4f [%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s]", m00, m01, m02, m03,
				m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
	}

}
