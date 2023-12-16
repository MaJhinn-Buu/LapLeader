package com.example.groupactivity

data class Race_Results(var gpyear : Int,
                        var gpdate : String,
                        var gplocation : String,
                        var gpnumOfLaps : Int,
                        var drivers : ArrayList<String>, )