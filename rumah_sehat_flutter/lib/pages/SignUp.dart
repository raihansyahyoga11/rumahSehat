import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:rumah_sehat_flutter/Screen/ProfilePage.dart';
import 'package:rumah_sehat_flutter/controller/SignUpController.dart';
import 'package:shared_preferences/shared_preferences.dart';

import 'HomePage.dart';
import '../main.dart';

class SignUpPage extends StatefulWidget {
  const SignUpPage({Key? key}) : super(key: key);

  @override
  State<SignUpPage> createState() => _SignUpPageState();
}

class _SignUpPageState extends State<SignUpPage> {

  void displayDialog(context, title, text) => showDialog(
    context: context,
    builder: (context) =>
        AlertDialog(
            title: Text(title),
            content: Text(text)
        ),
  );

  final ButtonStyle style = ElevatedButton.styleFrom(
      padding: EdgeInsets.all(20),
      backgroundColor: Colors.lightBlueAccent,
      textStyle: TextStyle(color: Colors.white),
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(20),
      ));

  SignUpController signUpController = SignUpController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text("Sign Up"),),
        body: Padding(
          padding: const EdgeInsets.all(3.0),
          child: SingleChildScrollView(
            child: Column(
            children: [
              SizedBox(
                height: 35,
              ),
              Padding(
                  padding: const EdgeInsets.symmetric(horizontal: 20),
                  child: Container(
                      alignment: Alignment.center,
                      padding: const EdgeInsets.all(10),
                      child: Text(
                        'Registrasi Pasien Baru',
                        style: TextStyle(fontSize: 20,
                                        fontWeight: FontWeight.bold,
                        color: Colors.grey[700]),

                      ))),
              SizedBox(
                height: 20,
              ),
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 20),
                child: SizedBox(
                  height: 40,
                  child: TextFormField(
                    controller: signUpController.usernameController,
                    autofocus: false,
                    // initialValue: 'Rose Angela',
                    decoration: InputDecoration(
                      hintText: 'Username',
                      contentPadding: EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(32.0)),
                    ),
                  ),
                ),
              ),
              SizedBox(
                height: 20,
              ),
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 20),
                child: SizedBox(
                  height: 40,
                  child: TextFormField(
                    autofocus: false,
                    //initialValue: 'some password',
                    controller: signUpController.passwordController,
                    obscureText: true,
                    decoration: InputDecoration(
                      hintText: 'Password',
                      contentPadding: EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(32.0)),
                    ),
                  ),
                ),
              ),
              SizedBox(
                height: 35,
              ),
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 20),
                child: SizedBox(
                  height: 40,
                  child: TextFormField(
                    autofocus: false,
                    //initialValue: 'some password',
                    controller: signUpController.nameController,
                    decoration: InputDecoration(
                      hintText: 'Nama',
                      contentPadding: EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(32.0)),
                    ),
                  ),
                ),
              ),
              SizedBox(
                height: 35,
              ),
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 20),
                child: SizedBox(
                  height: 40,
                  child: TextFormField(
                    autofocus: false,
                    //initialValue: 'some password',
                    controller: signUpController.emailController,
                    decoration: InputDecoration(
                      hintText: 'Email',
                      contentPadding: EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(32.0)),
                    ),
                  ),
                ),
              ),
              SizedBox(
                height: 35,
              ),
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 20),
                child: SizedBox(
                  height: 40,
                  child: TextFormField(
                    autofocus: false,
                    //initialValue: 'some password',
                    controller: signUpController.umurController,
                    keyboardType: TextInputType.number,
                    decoration: InputDecoration(
                      hintText: 'Umur',
                      contentPadding: EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(32.0)),
                    ),
                  ),
                ),
              ),
              SizedBox(
                height: 35,
              ),
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 20),
                child: SizedBox(
                  height: 40,
                  child: TextFormField(
                    autofocus: false,
                    //initialValue: 'some password',
                    controller: signUpController.roleController,
                    decoration: InputDecoration(
                      hintText: 'Role. Please type: "PASIEN"',
                      contentPadding: EdgeInsets.fromLTRB(20.0, 10.0, 20.0, 10.0),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(32.0)),
                    ),
                  ),
                ),
              ),
              SizedBox(
                height: 35,
              ),
              Padding(
                padding: EdgeInsets.symmetric(vertical: 16.0),
                child: ElevatedButton(
                  style: style,
                  onPressed: () async {
                    int code = await signUpController.attemptSignUp(signUpController.usernameController.text,
                                                                        signUpController.passwordController.text,
                                                                        signUpController.nameController.text,
                                                                        signUpController.emailController.text,
                                                                        signUpController.umurController.text,
                                                                        signUpController.roleController.text);
                    if (code == 200) {
                      Navigator.of(context).pushReplacement(MaterialPageRoute(
                        builder: (context) => Login(),
                      ));
                    } else {
                      Navigator.of(context).pushReplacement(MaterialPageRoute(
                        builder: (context) => const SignUpPage(),
                      ));
                    }
                  },
                  child: Text(
                    'Submit',
                    style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold)
                  ),
                ),
              ),
            ],
          ),
          ),
        )
    );
  }
}
