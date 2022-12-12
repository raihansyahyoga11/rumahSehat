import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';

class TagihanController {
  final TextEditingController kodeController = TextEditingController();

  Future<int> attemptPay(String kodeController) async {
    var url = 'https://apap-065.cs.ui.ac.id/api/v1/tagihan/pay/$kodeController';
    var response = await http.post(
      Uri.parse(url),
    );
    if (response.statusCode == 200) {
      return response.statusCode;
    } else {
      return response.statusCode;
    }
  }
}
