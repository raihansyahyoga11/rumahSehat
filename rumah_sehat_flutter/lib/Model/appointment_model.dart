import 'nonpasien_model.dart';

class Appointment {
  final String kode;
  final DateTime waktuAwal;
  final bool isDone;
  final Dokter dokter;
  final bool isResepExist;

  Appointment(
      {required this.kode,
      required this.waktuAwal,
      required this.isDone,
      required this.dokter,
      required this.isResepExist});

  factory Appointment.fromJson(Map<String, dynamic> json) => Appointment(
        kode: json["kode"],
        waktuAwal: DateTime.parse(json['waktuAwal']),
        isDone: json['isDone'] == "true" ? true : false,
        dokter: Dokter.fromJson(json["dokter"]),
        isResepExist: json['resep'] == null ? false : true,
      );

  Map<dynamic, dynamic> toJson() => {
        kode: kode,
        waktuAwal: waktuAwal,
        isDone: isDone,
        dokter: dokter,
        isResepExist: isResepExist,
      };
}
