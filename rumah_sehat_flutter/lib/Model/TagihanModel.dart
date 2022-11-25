import 'dart:async';
import 'dart:convert';
import 'dart:html';
import 'package:flutter_html/flutter_html.dart';
import 'package:http/http.dart' as http;
import 'package:json_annotation/json_annotation.dart';




class TagihanModel {
  final String kode;
  final LocalDateTimeInputElement tanggalTerbuat;
  final LocalDateTimeInputElement tanggalBayar;
  final bool isPaid;
  final int jumlahTagihan;
  final List<dynamic> listAppointment;



  TagihanModel({
    required this.kode,
    required this.tanggalTerbuat,
    required this.tanggalBayar,
    required this.isPaid,
    required this.jumlahTagihan,
    required this.listAppointment,
  });
  factory TagihanModel.fromJson(Map<String, dynamic> json) {
    return TagihanModel(
        kode: json['kode'],
        tanggalTerbuat: json['tanggal_terbuat'],
        tanggalBayar: json['tanggal_bayar'],
        isPaid: json['is_paid'],
        jumlahTagihan: json['jumlah_tagihan'],
        listAppointment: json['listAppointment']
    );
  }
}
