import 'dart:convert';

List<TagihanModel> tagihanModelFromJson(String str) => List<TagihanModel>.from(
    json.decode(str).map((x) => TagihanModel.fromJson(x)));

String tagihanModelToJson(List<TagihanModel> data) =>
    json.encode(List<dynamic>.from(data.map((x) => x.toJson())));

class TagihanModel {
  final String kode;
  final String tanggalTerbuat;
  final bool isPaid;
  final int jumlahTagihan;
  final String kodeAppointment;

  TagihanModel({
    required this.kode,
    required this.tanggalTerbuat,
    required this.isPaid,
    required this.jumlahTagihan,
    required this.kodeAppointment,
  });
  factory TagihanModel.fromJson(Map<String, dynamic> json) => TagihanModel(
        kode: json['kode'],
        tanggalTerbuat: json['tanggalTerbuat'],
        isPaid: json['isPaid'],
        jumlahTagihan: json['jumlahTagihan'],
        kodeAppointment: json['kodeAppointment'],
      );

  Map<String, dynamic> toJson() => {
        "kode": kode,
        "tanggalTerbuat": tanggalTerbuat,
        "isPaid": isPaid,
        "jumlahTagihan": jumlahTagihan,
        "kodeAppointment": kodeAppointment,
      };
}
