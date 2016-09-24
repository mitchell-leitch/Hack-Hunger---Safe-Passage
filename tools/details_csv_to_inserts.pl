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
  while ($line =~ s/,,/,0,/g) {};
  my @a = split(/,/, $line);

  #EntityName,SiteName,SiteNumber,ClaimCalendarMonth,ClaimCalendarYear,MealRateTypeDescription,ClaimDerivedCategoryTypeDescription,MealTypeDescription,MealCount,Enrol,ADA,Eligibles,DaysClaimed,ADP,StreetAddress,City,State,Zip,ZipPlus4,County
  # 0              1    2            3                   4                     5                      6                                 7                8         9    0     11        12       13        14       15    16   17  18       19
  print "INSERT INTO public.distributions(entityname, sitename, claimcalendarmonth, claimcalendaryear, mealcount, eligibles, daysclaimed, adp, streetaddress) values('$a[0]', '$a[1]', $a[3], $a[4], $a[8], $a[11], $a[12], $a[13], '$a[14]');\n";

}
