/*----------------------------------------------------------------*/
/* Elijah ******
/* get_stats is a function to figure the best time for each       */
/* racer, compute the all-over average of the best times, and     */
/* find the longest time and the shortest time on the track       */
#include "lab7.h"

void get_stats(driver_t driver_list[NRACERS],stats_t*race_stats){
    int d, t;  //driver, time

    race_stats->average_of_best=0.0;

    race_stats->winning_time=driver_list[0].tries[0];//fastest time
    race_stats->slowest_time=driver_list[0].tries[0];//slowest time

    for (d=0;d<NRACERS;d++){//iterate from 0 to nracers by one
        driver_list[d].deviation=0.0;
        driver_list[d].best_time=driver_list[d].tries[0];

        for (t = 0; t < TRIES; t++){//iterate by one from 0 to t 
            if(driver_list[d].tries[t]<driver_list[d].best_time){//find best time
                driver_list[d].best_time=driver_list[d].tries[t];
            }
            if(driver_list[d].tries[t]<race_stats->winning_time ){//find fastest and slowest time
                race_stats->winning_time=driver_list[d].tries[t];
            }
            if(driver_list[d].tries[t]>race_stats->slowest_time ){
                race_stats->slowest_time=driver_list[d].tries[t];
            }
        }
        race_stats->average_of_best+=driver_list[d].best_time;//add drivers best time to running total 
    }
    
    race_stats->average_of_best/=(double)NRACERS;//find average of best times
    
    for (d = 0; d < NRACERS; d++)//compute deviation 
    {
        driver_list[d].deviation = race_stats->winning_time - driver_list[d].best_time;
    }
    return;
}
