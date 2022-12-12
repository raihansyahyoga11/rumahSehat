// import 'dart:convert';
import 'package:flutter/material.dart';
// import 'package:rumah_sehat_flutter/controller/authentication_controller.dart';
// import 'package:rumah_sehat_flutter/pages/SignUp.dart';
// import '../main.dart';
import 'RumahSehatPage.dart';

class CreateAppointmentPage extends StatefulWidget {
  const CreateAppointmentPage({Key? key}) : super(key: key);

  @override
  State<CreateAppointmentPage> createState() => _CreateAppointmentPageState();
}

class _CreateAppointmentPageState extends State<CreateAppointmentPage> {



  final ButtonStyle style = ElevatedButton.styleFrom(
      padding: EdgeInsets.all(20),
      backgroundColor: Colors.lightBlueAccent,
      textStyle: TextStyle(color: Colors.white),
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(20),
      ));

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text("Buat Appointment"),),
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
                          'Buat Janji Baru Dengan Dokter',
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
                      // controller: signUpController.usernameController,
                      // autofocus: false,
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
                  height: 35,
                ),
                Padding(
                  padding: EdgeInsets.symmetric(vertical: 16.0),
                  child: ElevatedButton(
                    style: style,
                    onPressed: () async {
                      Navigator.of(context).pushReplacement(MaterialPageRoute(
                        builder: (context) => const RumahSehatMain(),
                      ));
                      // int code = await signUpController.attemptSignUp(signUpController.usernameController.text,
                      //     signUpController.passwordController.text,
                      //     signUpController.nameController.text,
                      //     signUpController.emailController.text,
                      //     signUpController.umurController.text,
                      //     signUpController.roleController.text);
                      // if (code == 200) {
                      //   Navigator.of(context).pushReplacement(MaterialPageRoute(
                      //     builder: (context) => Login(),
                      //   ));
                      // } else {
                      //   Navigator.of(context).pushReplacement(MaterialPageRoute(
                      //     builder: (context) => const SignUpPage(),
                      //   ));
                      // }
                    },
                    child: Text(
                        'Buat Appointment',
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