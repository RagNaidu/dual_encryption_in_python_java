def gcd(a, b):
    """Returns gcd of two integers"""
    while b != 0:
        c = a % b
        a = b
        b = c
    return a


def mod_inverse(a, m):
    """Returns modular inverse of two integers"""

    if gcd(a, m) != 1:
        return None

    u1, u2, u3 = 1, 0, a
    v1, v2, v3 = 0, 1, m

    while v3 != 0:
        q = u3 // v3
        v1, v2, v3, u1, u2, u3 = (u1 - q * v1), (u2 - q * v2), (u3 - q * v3), v1, v2, v3

    return u1 % m
