import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';

import '../Model/PasienModel.dart';

class AuthenticationController {
  final storage = new FlutterSecureStorage();
  String? token;
  TextEditingController usernameController = TextEditingController();
  TextEditingController passwordController = TextEditingController();
  TextEditingController saldoController = TextEditingController();

  Future<int> loginUser() async {
    const url = 'https://apap-065.cs.ui.ac.id/api/mobile/signin';
    var response = await http.post(Uri.parse(url),
        headers: {'Content-Type': 'application/json; charset=UTF-8'},
        body: jsonEncode({
          "username": usernameController.text,
          "password": passwordController.text,
        }));
    if (response.statusCode == 200) {
      var loginMaterial = json.decode(response.body);
      token = loginMaterial['token'];
      SharedPreferences prefrences = await SharedPreferences.getInstance();

      await storage.write(key: 'TOKEN', value :token);
      await storage.write(key: 'USERNAME', value :loginMaterial['username']);

      prefrences.setString('token', loginMaterial['token']);
      prefrences.setString('username', loginMaterial['username']);

      return response.statusCode;

    } else {
      return response.statusCode;
    }
  }


  logOut() async {
    SharedPreferences prefrences = await SharedPreferences.getInstance();
    prefrences.remove("token");
    token = null;
    prefrences.remove("token");
  }
  Future<int> attemptTopUp() async {
    const url = 'https://apap-065.cs.ui.ac.id/api/mobile/topUp/';
    String? token1 = await storage.read(key:'TOKEN');
    String? username1 = await storage.read(key:'USERNAME');
    var response = await http.put(Uri.parse(url),
        headers: {'Content-Type': 'application/json; charset=UTF-8',
          'Accept': 'application/json',
          'Authorization': 'Bearer $token1',
        },
        body: jsonEncode({
          "saldo": saldoController.text
        }));
    if (response.statusCode == 200) {
      return response.statusCode;
    } else {
      return response.statusCode;
    }
  }

  Future<PasienModel> getUserProfile() async {
    const url = 'https://apap-065.cs.ui.ac.id/api/mobile/profile/pasien';

    String? token1 = await storage.read(key:'TOKEN');
    var response = await http.get(Uri.parse(url),
        headers: {'Content-Type': 'application/json; charset=UTF-8',
          'Accept': 'application/json',
          'Authorization': 'Bearer $token1',
        });
    if (response.statusCode == 200) {

      var loginMaterial = json.decode(response.body);

      return PasienModel.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to load album');
    }
  }

}
