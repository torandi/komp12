/*
 * A random dungeon map generator in MiniJava. It uses a digging
 * algorithm adapted from a labyrinth-making algorithm. You can
 * customize the size (50x10 by default) and looks (by changing the
 * RNG seed) in the call to DungeonMapper.run(). Use the bfprint.awk
 * script to view the result in its full ASCII glory!
 */
class Dungeon {
    public static void main(String[] args) {
        System.out.println(new DungeonMapper().run(50, 10, 1234));
    }
}

class Cell {
    /* Types of walls : 0 = undef, 1 = none, 2 = door, 3 = wall. */
    int _w;
    int _s;
    int _e;
    int _n;
    /* Types de rooms : 0 = normal, 1 = entry, 2 = exit. */
    int _type;

    int _index;
    Cell _next;

    public Cell Cell(int i) {
        _index = i;
        _next = this;
        return this;
    }

    public int type() {
        return _type;
    }

    public int setType(int t) {
        _type = t;
        return 0;
    }

    public int getWall(int d) {
        int k;
        k = 0 - 1;
        if (d < 1) {
            k = _w;
        } else if (d < 2) {
            k = _s;
        } else if (d < 3) {
            k = _e;
        } else if (d < 4) {
            k = _n;
        } else {
            System.out.println(666666);
            /* throw new IllegalStateException(); */
        }
        return k;
    }

    public int setWall(int d, int k) {
        if (d < 1) {
            _w = k;
        } else if (d < 2) {
            _s = k;
        } else if (d < 3) {
            _e = k;
        } else if (d < 4) {
            _n = k;
        } else {
            System.out.println(666666);
            /* throw new IllegalStateException(); */
        }
        return 0;
    }

    public int index() {
        return _index;
    }

    public Cell next() {
        return _next;
    }

    public Cell setNext(Cell r) {
        _next = r;
        return r;
    }
}

class List {
    Cell _head;

    public List List() {
        _head = new Cell().Cell(0 - 1);
        return this;
    }

    public Cell cons(int i, Cell q) {
        Cell p;
        Cell r;

        if (!(q.index() < 0) && !(q.index() < i)) {
            System.out.println(666666);
            /* throw new IllegalStateException(); */
        } else {
        }
        p = new Cell().Cell(i);
        r = p.setNext(q.next());
        r = q.setNext(p);
        return p;
    }

    public Cell get(int i) {
	Cell p;
        Cell q;

        q = _head;
        p = _head.next();
        while (!(p.index() < 0) && p.index() < i) {
            q = p;
            p = p.next();
        }
        if (!(p.index() < i + 1 && !(p.index() < i))) {
            p = this.cons(i, q);
        } else {
        }
        return p;
    }
}

class RNG {
    int _seed;

    public RNG RNG(int seed) {
        _seed = seed;
        return this;
    }

    public int cap(int n) {
        int r;

        if (!(n < 0)) {
            r = n;
        } else {
            r = n - 2147483647 - 1;
        }
        return r;
    }

    public int div(int n, int p) {
        int d;

        d = 0;
        while (p < n + 1) {
            d = d + 1;
            n = n - p;
        }
        return d;
    }

    public int mod(int n, int p) {
        while (p < n + 1) {
            n = n - p;
        }
        return n;
    }

    public int rand(int n) {
        int r;

        _seed = this.cap(_seed * 1103515245 + 12345);
        _seed = this.mod(this.div(_seed, 65536), 32768);
        if (n < 1) {
            r = 0;
        } else {
            r = this.mod(_seed, n);
        }
        return r;
    }
}

class Map {
    RNG _rng;
    List _ground;
    int _w;
    int _h;

    public Map Map(RNG rng, int w, int h) {
        _rng = rng;
        _ground = new List().List();
        _w = w;
        _h = h;
        return this;
    }

    public boolean eq(int x, int y) {
        return x < y + 1 && !(x < y);
    }

    public int getXNeighbor(int x, int y, int d) {
        int r;

        r = 0 - 1;
        if (d < 1) {
            if (!this.eq(x, 0)) {
                r = x - 1;
            } else {
            }
        } else if (d < 2) {
            if (!this.eq(y, _h - 1)) {
                r = x;
            } else {
            }
        } else if (d < 3) {
            if (!this.eq(x, _w - 1)) {
                r = x + 1;
            } else {
            }
        } else if (d < 4) {
            if (!this.eq(y, 0)) {
                r = x;
            } else {
            }
        } else {
            System.out.println(666666);
            /* throw new IllegalStateException(); */
        }
        return r;
    }

    public int getYNeighbor(int x, int y, int d) {
        int r;

        r = 0 - 1;
        if (d < 1) {
            if (!this.eq(x, 0)) {
                r = y;
            } else {
            }
        } else if (d < 2) {
            if (!this.eq(y, _h - 1)) {
                r = y + 1;
            } else {
            }
        } else if (d < 3) {
            if (!this.eq(x, _w - 1)) {
                r = y;
            } else {
            }
        } else if (d < 4) {
            if (!this.eq(y, 0)) {
                r = y - 1;
            } else {
            }
        } else {
            System.out.println(666666);
            /* throw new IllegalStateException(); */
        }
        return r;
    }

    public Cell at(int x, int y) {
        return _ground.get(y * _w + x);
    }

    public int setOppositeWall(int x, int y, int rd, int k) {
        int d;

        d = 0 - 1;
        if (rd < 1) {
            d = 2;
        } else if (rd < 2) {
            d = 3;
        } else if (rd < 3) {
            d = 0;
        } else if (rd < 4) {
            d = 1;
        } else {
            System.out.println(666666);
            /* throw new IllegalStateException(); */
        }

        return this.at(x, y).setWall(d, k);
    }

    public int dig(int x, int y) {
	Cell p;
        Cell q;
        int d;
        int z;
        int t;
        int r;
        boolean notend;
        boolean qvisited;

        r = 0;

        notend = false;
        p = this.at(x, y);

        while (!(!this.eq(p.getWall(0), 0) && !this.eq(p.getWall(1), 0) &&
                 !this.eq(p.getWall(2), 0) && !this.eq(p.getWall(3), 0))) {
            d = _rng.rand(4);
            while (!this.eq(p.getWall(d), 0)) {
                d = _rng.rand(4);
            }
            z = this.getXNeighbor(x, y, d);
            t = this.getYNeighbor(x, y, d);
            qvisited = false;
            if (!(z < 0)) {
                q = this.at(z, t);
                qvisited = !(this.eq(q.getWall(0), 0) &&
                             this.eq(q.getWall(1), 0) &&
                             this.eq(q.getWall(2), 0) &&
                             this.eq(q.getWall(3), 0));
            } else {
            }
            if (!(!(z < 0) && !qvisited)) {
                r = p.setWall(d, 3);
                if (!this.eq(z, 0 - 1)) {
                    r = this.setOppositeWall(z, t, d, p.getWall(d));
                } else {
                }
            } else {
                r = p.setWall(d, _rng.rand(2) + 1);
                r = this.setOppositeWall(z, t, d, p.getWall(d));
                r = this.dig(z, t);
                notend = true;
            }
        }
        if (!notend) {
            r = p.setType(2);
        } else {
        }
        return r;
    }

    public int startDig(int x, int y) {
        int r;

        r = this.at(x, y).setType(1);
        r = this.dig(x, y);
        return r;
    }

    public int write() {
	Cell p;
        int x;
        int y;

        y = 0;
        while (y < _h) {
            x = 0;
            while (x < _w) {
                p = this.at(x, y);
                if (this.eq(p.getWall(3), 1)) {
                    System.out.println(35);
                    System.out.println(32);
                } else if (this.eq(p.getWall(3), 2)) {
                    System.out.println(35);
                    System.out.println(43);
                } else if (this.eq(p.getWall(3), 3)) {
                    System.out.println(35);
                    System.out.println(35);
                } else {
                    System.out.println(666666);
                    /* throw new IllegalStateException(); */
                }
                if (this.eq(x, _w - 1)) {
                    System.out.println(35);
                } else {
                }
                x = x + 1;
            }
            System.out.println(10);
            x = 0;
            while (x < _w) {
                p = this.at(x, y);
                if (this.eq(p.getWall(0), 1)) {
                    System.out.println(32);
                } else if (this.eq(p.getWall(0), 2)) {
                    System.out.println(43);
                } else if (this.eq(p.getWall(0), 3)) {
                    System.out.println(35);
                } else {
                    System.out.println(666666);
                    /* throw new IllegalStateException(); */
                }
                if (this.eq(p.type(), 0)) {
                    System.out.println(32);
                } else if (this.eq(p.type(), 1)) {
                    System.out.println(94);
                } else if (this.eq(p.type(), 2)) {
                    System.out.println(36);
                } else {
                    System.out.println(666666);
                    /* throw new IllegalStateException(); */
                }
                if (this.eq(x, _w - 1)) {
                    if (this.eq(p.getWall(2), 1)) {
                        System.out.println(32);
                    } else if (this.eq(p.getWall(2), 2)) {
                        System.out.println(43);
                    } else if (this.eq(p.getWall(2), 3)) {
                        System.out.println(35);
                    } else {
                        System.out.println(666666);
                        /* throw new IllegalStateException(); */
                    }
                } else {
                }
                x = x + 1;
            }
            System.out.println(10);
            if (this.eq(y, _h - 1)) {
                x = 0;
                while (x < _w) {
                    p = this.at(x, y);
                    if (this.eq(p.getWall(1), 1)) {
                        System.out.println(35);
                        System.out.println(32);
                    } else if (this.eq(p.getWall(1), 2)) {
                        System.out.println(35);
                        System.out.println(43);
                    } else if (this.eq(p.getWall(1), 3)) {
                        System.out.println(35);
                        System.out.println(35);
                    } else {
                        System.out.println(666666);
                        /* throw new IllegalStateException(); */
                    }
                    x = x + 1;
                    if (this.eq(x, _w - 1)) {
                        System.out.println(35);
                    } else {
                    }
                }
                System.out.println(10);
            } else {
            }
            y = y + 1;
        }

        return 0;
    }
}

class DungeonMapper {
    public int run(int w, int h, int seed) {
        RNG rng;
        Map map;
        int r;

	rng = new RNG().RNG(seed);
	map = new Map().Map(rng, w, h);
	r = map.startDig(rng.rand(3), rng.rand(3));
	r = map.write();

        return r;
    }
}
