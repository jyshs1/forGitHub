;Program: 两个数之和
;Author:
;Date: 三月十五

.386
.MODEL FLAT

ExitProcess PROTO NEAR32 stdcall, dwExitCode:DWORD

INCLUDE io.h            ;  输入/输出的头文件

cr      EQU     0dh     ;  回车符
Lf      EQU     0ah     ;  换行

.STACK  4096            ;  保留4096字节的堆栈

.DATA                   ;  数据保留区
number1 DWORD   ?
number2 DWORD   ?
prompt1 BYTE    "Enter first number:  ", 0
prompt2 BYTE    "Enter second number:  ", 0
string  BYTE     40 DUP (?)
label1  BYTE    cr, Lf, "The sum is "
sum     BYTE    11 DUP (?)
        BYTE    cr, Lf, 0

.CODE                           ; 程序代码开始
_start:
        output  prompt1         ; 提示输入第一个数
        input   string, 40      ; 读取
        atod    string          ;
        mov     number1, eax    ;

        output  prompt2         ;
        input   string, 40
        atod    string
        mov     number2, eax

        mov     eax, number1    ;
        add     eax, number2    ;
        dtoa    sum, eax        ;
        output  label1          ;


        INVOKE  ExitProcess, 0  ; exit with return code 0
PUBLIC _start                   ; make entry point public

END                             ; end of source code