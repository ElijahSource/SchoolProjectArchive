/*--------------------------------------------------------*/
/* Elijah ******                                              */
 
#include "lab7.h"

void get_median(driver_t driver_list[NRACERS],stats_t*race_stats)
{
    int mid;
    race_stats->median=0.0;
    mid = NRACERS/2;//calculate the midpoint

    if (NRACERS%2!=0){//if nracers is odd
    	race_stats->median=driver_list[mid].best_time;//set median to the mid average
    }
    else{
        race_stats->median=(driver_list[mid-1].best_time+driver_list[mid].best_time)/2.0 ;//set the median to the average of the two numbers on each side of the median     
    }
    return;
}
