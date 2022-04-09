# SMI VTT converter.Converter

## Goal
- Converts VTT to SMI

## WebVTT Spec
- https://www.w3.org/TR/webvtt1/

## SMI Spec
- https://docs.microsoft.com/ko-kr/previous-versions/windows/desktop/dnacc/understanding-sami-1.0?redirectedfrom=MSDN

## Build
```
mvn package
```

## Usage
```
java -jar vtt-converter.jar [input.vtt] [output.smi]

Program arguments: sample.vtt, sample.smi

File read from /Users/user/Desktop/temp/sample.vtt : 
WEBVTT

1
00:00:00.500 --> 00:00:04.000
Aprendi a me virar e você voltou

2
00:00:04.100 --> 00:00:06.000
do espaço sideral

3
00:00:06.100 --> 00:00:10.500
e agora vejo que você está aqui no baixo astral

4
00:00:10.600 --> 00:00:14.000
eu devia me mudar e ter tomado a sua chave

5
00:00:14.100 --> 00:00:17.500
se soubesse que ia voltar para mim enfernizar

6
00:00:17.600 --> 00:00:19.500
agora vai, sai daqui

7
00:00:19.600 --> 00:00:21.500
Frank!

8
00:00:21.600 --> 00:00:24.100
ponha a cabeça para dentro

9
00:00:24.200 --> 00:00:26.500
antes que eu emprense nessa janela

10
00:00:26.600 --> 00:00:29.500
tá legal

11
00:00:29.600 --> 00:00:33.500
hhmm... hhmmm... hmmm...

12
00:00:33.600 --> 00:00:34.000
FRANK!!!

Result on /Users/user/Desktop/temp/sample.smi : 
<SAMI>
<HEAD>
<TITLE></TITLE>
<STYLE TYPE="text/css">
<!--
P { margin-left:8pt; margin-right:8pt; margin-bottom:2pt;
margin-top:2pt; font-size:14pt; text-align:center;
font-family:굴림, Arial; font-weight:normal; color:white;
background-color:black; }
.KRCC { Name: 한국어; lang: ko-KR; SAMIType: CC; }
#STDPrn { Name:Standard Print; }
#LargePrn { Name:Large Print; font-size:20pt; }
#SmallPrn { Name:Small Print; font-size:10pt; }
--></STYLE>
</HEAD>
<BODY>
<SYNC Start=500>
<P Class=KRCC>Aprendi a me virar e você voltou</P>
<SYNC Start=4000>
<P Class=KRCC>&nbsp;</P>
<SYNC Start=4100>
<P Class=KRCC>do espaço sideral</P>
<SYNC Start=6000>
<P Class=KRCC>&nbsp;</P>
<SYNC Start=6100>
<P Class=KRCC>e agora vejo que você está aqui no baixo astral</P>
<SYNC Start=10500>
<P Class=KRCC>&nbsp;</P>
<SYNC Start=10600>
<P Class=KRCC>eu devia me mudar e ter tomado a sua chave</P>
<SYNC Start=14000>
<P Class=KRCC>&nbsp;</P>
<SYNC Start=14100>
<P Class=KRCC>se soubesse que ia voltar para mim enfernizar</P>
<SYNC Start=17500>
<P Class=KRCC>&nbsp;</P>
<SYNC Start=17600>
<P Class=KRCC>agora vai, sai daqui</P>
<SYNC Start=19500>
<P Class=KRCC>&nbsp;</P>
<SYNC Start=19600>
<P Class=KRCC>Frank!</P>
<SYNC Start=21500>
<P Class=KRCC>&nbsp;</P>
<SYNC Start=21600>
<P Class=KRCC>ponha a cabeça para dentro</P>
<SYNC Start=24100>
<P Class=KRCC>&nbsp;</P>
<SYNC Start=24200>
<P Class=KRCC>antes que eu emprense nessa janela</P>
<SYNC Start=26500>
<P Class=KRCC>&nbsp;</P>
<SYNC Start=26600>
<P Class=KRCC>tá legal</P>
<SYNC Start=29500>
<P Class=KRCC>&nbsp;</P>
<SYNC Start=29600>
<P Class=KRCC>hhmm... hhmmm... hmmm...</P>
<SYNC Start=33500>
<P Class=KRCC>&nbsp;</P>
<SYNC Start=33600>
<P Class=KRCC>FRANK!!!</P>
<SYNC Start=34000>
<P Class=KRCC>&nbsp;</P>
</BODY>
</SAMI>

```
