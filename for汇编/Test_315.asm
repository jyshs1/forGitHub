;Program: ������֮��
;Author:
;Date: ����ʮ��

.386
.MODEL FLAT

ExitProcess PROTO NEAR32 stdcall, dwExitCode:DWORD

INCLUDE io.h            ;  ����/�����ͷ�ļ�

cr      EQU     0dh     ;  �س���
Lf      EQU     0ah     ;  ����

.STACK  4096            ;  ����4096�ֽڵĶ�ջ

.DATA                   ;  ���ݱ�����
number1 DWORD   ?
number2 DWORD   ?
prompt1 BYTE    "Enter first number:  ", 0
prompt2 BYTE    "Enter second number:  ", 0
string  BYTE     40 DUP (?)
label1  BYTE    cr, Lf, "The sum is "
sum     BYTE    11 DUP (?)
        BYTE    cr, Lf, 0

.CODE                           ; ������뿪ʼ
_start:
        output  prompt1         ; ��ʾ�����һ����
        input   string, 40      ; ��ȡ
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