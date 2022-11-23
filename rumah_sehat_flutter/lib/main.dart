import 'package:flutter/material.dart';

import 'Screen/TopUpPage.dart';
import 'Screen/ProfilePage.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Rumah Sehat",
      debugShowCheckedModeBanner: false,
      home: RumahSehatNavBar(),
    );
  }
}

class RumahSehatNavBar extends StatefulWidget {
  @override
  _RumahSehatNavBarState createState() => _RumahSehatNavBarState();
}

class _RumahSehatNavBarState extends State<RumahSehatNavBar> {
  int _selectedNavbar = 0;
  //navigasi page
  final screens=[
    Center(child:Text('Home', style:TextStyle(fontSize: 60))),
    Center(child:Text('Appointment', style:TextStyle(fontSize: 60))),
    Center(child:Text('Obat', style:TextStyle(fontSize: 60))),
    TesPage(),
  ];

  void _changeSelectedNavBar(int index) {
    setState(() {
      _selectedNavbar = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Rumah Sehat"),
      ),
      body: screens[_selectedNavbar],
      bottomNavigationBar: BottomNavigationBar(
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Icon(Icons.home),
            title: Text('Beranda'),
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.assignment),
            title: Text('Appointment'),
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.medication_outlined ),
            title: Text('Obat'),
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.account_circle_outlined),
            title: Text('Akun'),
          ),
        ],
        currentIndex: _selectedNavbar,
        selectedItemColor: Colors.blue,
        unselectedItemColor: Colors.grey,
        showUnselectedLabels: true,
        onTap: _changeSelectedNavBar,
      ),
    );
  }
}