package com.example.groupactivity

data class Race_Results(var GPYear : Int,
                        var GPDate : String,
                        var GPLocation : String,
                        var GPNumOfLaps : Int,
                        var Drivers : ArrayList<String>,
                        var Teams : ArrayList<String>)