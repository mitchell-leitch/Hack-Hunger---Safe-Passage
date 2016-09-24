#!/usr/bin/env perl 
#===============================================================================
#
#         FILE: csv_to_inserts.pl
#
#        USAGE: ./csv_to_inserts.pl  
#
#  DESCRIPTION: 
#
#      OPTIONS: ---
# REQUIREMENTS: ---
#         BUGS: ---
#        NOTES: ---
#       AUTHOR: YOUR NAME (), 
# ORGANIZATION: 
#      VERSION: 1.0
#      CREATED: 09/24/2016 11:20:19 AM
#     REVISION: ---
#===============================================================================

use strict;
use warnings;
use utf8;

while (my $line = <>){
  chomp $line;
  $line =~ s/'//g;
  $line =~ s/\r//g;
  $line =~ s/\n//g;
  #print "$line\n";
  my @a = split(/,/, $line);
  # Street Address,City,State,Zip,Lat,Long,Address without quote,Distribution Name,IsSchool,Breakfast,Lunch,Supper,P.M. Snack 
  # 0               1    2     3  4   5       6                       7               8      9        10     11     12
  print "INSERT INTO public.depositories(name, streetAddress, city, state, zip,  geom, isSchool, hasBreakfast, hasLunch, hasSupper, hasPMSnack) values('$a[7]', '$a[0]', '$a[1]', '$a[2]', '$a[3]', ST_POINT($a[5], $a[4]), B'$a[8]', B'$a[9]', B'$a[10]', B'$a[11]', B'$a[12]');\n";

}
