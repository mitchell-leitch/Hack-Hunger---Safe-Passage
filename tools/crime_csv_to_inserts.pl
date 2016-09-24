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
  $line =~ s/, /,/g;
  my @a = split(/,/, $line);
  # ID, crimetime, lat, long
  #  0    1        2     3  
  print "INSERT INTO public.crimes(id, crimetime, geom) values($a[0], '$a[1]', ST_POINT($a[3], $a[2]));\n";

}
