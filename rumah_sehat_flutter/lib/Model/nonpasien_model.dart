class Dokter {
  final String uuid;
  final String nama;
  final String username;
  final int tarif;

  Dokter(
      {required this.uuid,
      required this.nama,
      required this.username,
      required this.tarif});

  factory Dokter.fromJson(Map<String, dynamic> json) => Dokter(
        uuid: json["uuid"],
        nama: json["nama"],
        username: json["username"],
        tarif: json["tarif"],
      );

  Map<String, dynamic> toJson() => {
        "uuid": uuid,
        "nama": nama,
        "username": username,
        "tarif": tarif,
      };
}
