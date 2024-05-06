# Tucil3_13522065
Tugas Kecil 3 IF2211 Strategi Algoritma: 
Penyelesaian Permainan Word Ladder Menggunakan Algoritma UCS, Greedy Best First Search, dan A*

## Deskripsi Singkat
Program ini merupakan solver untuk Permainian Word Ladder, menggunakan 3 pilihan Algoritma Route Planning: UCS, Greedy Best First Search, dan A*.

## Requirements
Pastikan sebelumnya mesin telah terinstall java, dengan mengetik 
```
java -version
```
di dalam command prompt untuk mengecek versi java. Sebagai referensi, kode dites menggunakan versi java (JDK) 21. Jika java belum terinstall, ikuti panduan installasi JDK dalam [Installasi java](tps://www.oracle.com/id/java/technologies/downloads/).

## Menjalankan Program

Menggunaakan terminal, Masuk ke dalam folder kode /src dengan perintah
```
cd src
```
Lalu compile semua java file dengan
```
javac Main.java
```
jalankan Main.class dengan perintah
```
java Main
```

## Menggunakan Program
Masukkan start word dan end word sesuai instruksi program, lalu masukkan algoritma 1,2, atau 3 untuk menggunakan algoritma UCS, GBFS, atau A* secara berturut. Input selain 1,2,3 akan digunakan A* secara automatis. Hasil program akan menampilkan solusi word Solver, jumlah visited word, jumlah steps, dan waktu eksekusi.