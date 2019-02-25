#IF3111 Platform Based Development

#Smart Door

###Deskripsi umum

Smart-door dibuat dengan arduino dan 2 buah android. Pada smart-door terdapat sensor suara dan infrared yang terhubung dengan arduino. Sensor suara akan digunakan untuk menerima input ketukan sedangkan sensor infrared digunakan untuk mendeteksi keberadaan orang di depan pintu. Android dalam keadaan stand-by yang berada di dalam pintu digunakan untuk berkomunikasi dengan android yang dimiliki oleh user. 

Android yang dimiliki user akan berfungsi untuk berkomunikasi dengan android yang standby di belakang pintu. User dapat menyimpan pattern ketukan dan mengasosiasikan setiap ketukan dengan identitas seseorang. Dengan demikian, smart door dapat mengetahui identitas pengunjung yang datang. Apabila ketukan tidak sesuai dengan pattern yang ada, pengunjung dapat menekan tombol yang akan memberikan notifikasi pada user sehingga user dapat membuka pintu secara wireless.

Android (client) menggunakan sensor significant motion untuk mengecek adanya pergerakan user, kemudian mengaktifkan google location service untuk mengecek apakah user sudah meninggalkan rumah dan selanjutnya mengunci pintu apabila pintu belum terkunci. Android (door) juga memanfaatkan wake-up sensor (proximity) untuk menyalakan layar android jika mendeteksi ada user yang mendekat.

#Panduan Instalasi Sistem

Buka project pada Android Studio dan lakukan run project untuk menginstalasi sistem ke android phone / emulator. 

#Penggunaan

1. Masuk ke dalam aplikasi dengan melaukan sign-in dengan menekan tombol Google Sign-In yang tersedia.
2. Setelah login berhasil, user dapat melakukan beberapa hal berikut :
	a. Penguncian pintu (auto lock) dengan menekan card yang menunjukkan rumah milik user.
	b. Menambahkan user pada tab user. Penambahan user dapat dilakukan dengan menekan tombol + yang tersedia dan penghapusan user dengan menekan nama user.
	c. Tab hisory akan menunjukan history dari penguncian yang pernah dilakukan.
	d. User dapat mengubah setting dengan menekan overflow button (titik tiga di pojok kanan atas).
	e. User dapat melakukan panggilan telpon kepada android pintu dengan menekan icon berlambang telepon di appbar.
	f. User dapat melakukan sign-out dengan menekan tombol sign-out yang terletak pada home tab di paling bawah.


#Letak Deliverables

Proposal Final : https://drive.google.com/open?id=1gFn4WxWMlSn2Ot_Vmy6vSBjtwwZKYJm4

Laporan : https://drive.google.com/open?id=12Jr2TBPfH3nbN9rw5FEsFB6RjjyhKhF_

Gitlab : https://gitlab.informatika.org/if3111-2019-knockknock/mobile


#Created by

Christian Kevin Saputra 13516073
Ahmad Faishol Huda 13516094
Jessin Donnyson 13516112