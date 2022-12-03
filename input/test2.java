package com.deneme;

public class A {
	static void m1() {
		m2();
		B.m2();
	}

	static void m2() {
		B.m1();
	}
}

class B {
	static void m1() {
		A.m1();
    B.m2();
	}

	static void m2() {
		C.m3();
	}
}

class C {
	static void m1() {
    B.m3();
  }
}

public class D {
	static void m1() {
    C.m1();
  }
  static void m2() {}
  static void m3() {}
}
