/*
 * A Brainfuck interpreter in MiniJava, without input support, with
 * its hello world program. Use bf2java.l (lex script) to generate
 * MiniJava code to replace load(), according to the following table:
 *
 * > 	0       ++index;
 * < 	1       --index;
 * + 	2       ++memory[index];
 * - 	3       --memory[index];
 * . 	4       System.out.println(memory[index]);
 * , 	5       not implemented
 * [ 	6       while (memory[index]) {
 * ] 	7       }
 *
 * Use bfprint.awk to print the output in full ASCII glory!
 */
class Brainfuck {
    public static void main(String[] args) {
        System.out.println(new Bf().run());
    }
}

class Bf {
    int[] memory;
    int index;
    int[] program;
    int pc;

    public int run() {
        int r;
        r = this.init();
        r = this.eval();
        return r;
    }

    public int init() {
        memory = new int[100];
        index = 0;

        program = new int[111];
        pc = 0;

        return this.load();
    }

    public boolean eq(int x, int y) {
        return x < y + 1 && !(x < y);
    }

    public int eval() {
        int r;

        pc = 0;
        r = 1;
        while (!(r < 1)) {
            if (!(pc < program.length)) {
                r = 0;
            } else if (this.eq(program[pc], 0)) {
                index = index + 1;
                pc = pc + 1;
            } else if (this.eq(program[pc], 1)) {
                index = index - 1;
                pc = pc + 1;
            } else if (this.eq(program[pc], 2)) {
                memory[index] = memory[index] + 1;
                pc = pc + 1;
            } else if (this.eq(program[pc], 3)) {
                memory[index] = memory[index] - 1;
                pc = pc + 1;
            } else if (this.eq(program[pc], 4)) {
                System.out.println(memory[index]);
                pc = pc + 1;
            } else if (this.eq(program[pc], 6)) {
                if (this.eq(memory[index], 0)) {
                    r = this.skip();
                } else {
                    pc = pc + 1;
                }
            } else if (this.eq(program[pc], 7)) {
                r = this.loop();
            } else {
                r = 0 - 1;
            }
        }

        return r;
    }

    public int skip() {
        int nested;

        nested = 0;
        while (!(nested < 2 && this.eq(program[pc], 7))) {
            if (this.eq(program[pc], 6)) {
                nested = nested + 1;
            } else if (this.eq(program[pc], 7)) {
                nested = nested - 1;
            } else {
            }
            pc = pc + 1;
        }
        pc = pc + 1;

        return 1;
    }

    public int loop() {
        int nested;

        pc = pc - 1;
        nested = 0;
        while (!(nested < 1 && this.eq(program[pc], 6))) {
            if (this.eq(program[pc], 6)) {
                nested = nested - 1;
            } else if (this.eq(program[pc], 7)) {
                nested = nested + 1;
            } else {
            }
            pc = pc - 1;
        }

        return 1;
    }

    public int load() {
        program[0] = 2;
        program[1] = 2;
        program[2] = 2;
        program[3] = 2;
        program[4] = 2;
        program[5] = 2;
        program[6] = 2;
        program[7] = 2;
        program[8] = 2;
        program[9] = 2;

        program[10] = 6;

        program[11] = 0;
        program[12] = 2;
        program[13] = 2;
        program[14] = 2;
        program[15] = 2;
        program[16] = 2;
        program[17] = 2;
        program[18] = 2;

        program[19] = 0;
        program[20] = 2;
        program[21] = 2;
        program[22] = 2;
        program[23] = 2;
        program[24] = 2;
        program[25] = 2;
        program[26] = 2;
        program[27] = 2;
        program[28] = 2;
        program[29] = 2;

        program[30] = 0;
        program[31] = 2;
        program[32] = 2;
        program[33] = 2;

        program[34] = 0;
        program[35] = 2;

        program[36] = 1;
        program[37] = 1;
        program[38] = 1;
        program[39] = 1;
        program[40] = 3;

        program[41] = 7;

        program[42] = 0;
        program[43] = 2;
        program[44] = 2;
        program[45] = 4;

        program[46] = 0;
        program[47] = 2;
        program[48] = 4;

        program[49] = 2;
        program[50] = 2;
        program[51] = 2;
        program[52] = 2;
        program[53] = 2;
        program[54] = 2;
        program[55] = 2;
        program[56] = 4;

        program[57] = 4;

        program[58] = 2;
        program[59] = 2;
        program[60] = 2;
        program[61] = 4;

        program[62] = 0;
        program[63] = 2;
        program[64] = 2;
        program[65] = 4;

        program[66] = 1;
        program[67] = 1;
        program[68] = 2;
        program[69] = 2;
        program[70] = 2;
        program[71] = 2;
        program[72] = 2;
        program[73] = 2;
        program[74] = 2;
        program[75] = 2;
        program[76] = 2;
        program[77] = 2;
        program[78] = 2;
        program[79] = 2;
        program[80] = 2;
        program[81] = 2;
        program[82] = 2;
        program[83] = 4;

        program[84] = 0;
        program[85] = 4;

        program[86] = 2;
        program[87] = 2;
        program[88] = 2;
        program[89] = 4;

        program[90] = 3;
        program[91] = 3;
        program[92] = 3;
        program[93] = 3;
        program[94] = 3;
        program[95] = 3;
        program[96] = 4;

        program[97] = 3;
        program[98] = 3;
        program[99] = 3;
        program[100] = 3;
        program[101] = 3;
        program[102] = 3;
        program[103] = 3;
        program[104] = 3;
        program[105] = 4;

        program[106] = 0;
        program[107] = 2;
        program[108] = 4;

        program[109] = 0;
        program[110] = 4;

        return 0;
    }
}
