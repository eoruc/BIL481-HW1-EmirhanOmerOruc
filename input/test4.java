package paketpaket;

public class A {
	static void m1() {
		m2();
		B.m2();
	}

	static void m2() {
		B.m1();
	}
  static void m3() {
		B.m1();
	}
  static void m4() {
		B.m2();
	}
  static void m5() {
		B.m3();
	}
  static void emptyMethodA() {}
}

class B {
	static void m1() {
		A.m1();
    B.m2();
    C.m5();
		C.m6();
		C.m7();
		C.m8();
		C.m9();
	}

	static void m2() {
		C.m3();
	}
  static void emptyMethodB() {}
}
