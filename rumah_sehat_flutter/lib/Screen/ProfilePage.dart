import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:rumah_sehat_flutter/Model/PasienModel.dart';

import 'TopUpPage.dart';



Future<PasienModel> fetchAlbum() async {
  String url = "localhost:8080";
  final response = await http.get(Uri.http(url, '/api/v1/profile/pasien/pasien1'));
  if (response.statusCode == 200) {
    // If the server did return a 200 OK response,
    // then parse the JSON.
    return PasienModel.fromJson(jsonDecode(response.body));
  } else {
    // If the server did not return a 200 OK response,
    // then throw an exception.
    throw Exception('Failed to load album');
  }
}

class ProfilePage extends StatefulWidget {

  @override
  State<ProfilePage> createState() => _ProfilePageState();

}

class _ProfilePageState extends State<ProfilePage> {
  late Future<PasienModel> futureAlbum;


  @override
  void initState() {
    super.initState();
    futureAlbum = fetchAlbum();

  }


  @override
  Widget build(BuildContext context) {

    return MaterialApp(
      title: 'Fetch Data Example',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: Scaffold(
      body:SizedBox(
          width: double.infinity,
          child:
          FutureBuilder<PasienModel>(
            future: futureAlbum,
            builder: (context, snapshot) {
            if (snapshot.hasData) {
            return ListView(
              padding: const EdgeInsets.all(8),
              children: <Widget>[
                Container(
                  height: 100,
                  child: Center(child: Icon(Icons.male)),
                ),
                Container(
                  height: 50,
                  child: ListTile(
                      title: Text(
                          'Nama',
                           style: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 17
                        )
                      ),
                      subtitle: Text(
                        snapshot.data!.nama,
                        style: TextStyle(
                        fontWeight: FontWeight.bold,
                        fontSize:12
                        )
                      )
                  ),
                ),
                Container(
                  height: 50,
                  child: ListTile(
                      title: Text(
                          'Username',
                          style: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 17
                          )
                      ),
                      subtitle: Text(
                          snapshot.data!.username,
                          style: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize:12
                          )
                      )
                  ),
                ),
                Container(
                  height: 50,
                  child: ListTile(
                      title: Text(
                          'Email',
                          style: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 17
                          )
                      ),
                      subtitle: Text(
                          snapshot.data!.email,
                          style: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 12
                          )
                      )
                  ),
                ),
                Container(
                  height: 100,
                  child: ListTile(
                      title: Text(
                          'Saldo',
                          textAlign: TextAlign.center,
                          style: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 20
                          )
                      ),
                      subtitle: Text(
                          snapshot.data!.saldo.toString(),
                          textAlign: TextAlign.center,
                          style: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 30
                          )
                      )
                  ),
                ),
                Container(
                    child: TextButton(
                      style: ButtonStyle(
                        foregroundColor: MaterialStateProperty.all<Color>(Colors.blue),
                      ),
                      onPressed: () {
                          Navigator.push(
                          context,
                          MaterialPageRoute(builder: (context) => TopUpPage()),
                          );
                        },
                      child: Text('TextButton'),
                    )
                ),
              ],
             );
            } else if (snapshot.hasError) {
                return Text('${snapshot.error}');
              }
            //By default, show a loading spinner.
              return const CircularProgressIndicator();
            }),

        ),
      )
    );
  }



}

//width: double.infinity,
//child: FutureBuilder<PasienModel>(
//future: futureAlbum,
//builder: (context, snapshot) {
  //if (snapshot.hasData) {
  //return Text(snapshot.data!.nama);
  //} else if (snapshot.hasError) {
  //return Text('${snapshot.error}');
  //}

  // By default, show a loading spinner.
  //return const CircularProgressIndicator();
  //},
//),