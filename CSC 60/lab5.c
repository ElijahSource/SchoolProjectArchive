/*-------------------------------------------------*/
/* Elijah ******                                       */
/* Lab 5                                           */
/* Area of inscribed polygon   */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define IN_FILE_NAME "lab5.dat"
#define OUT_FILE_NAME "lab5.txt"

int main(void)
{
    double nsides, radius, area;
    FILE *infile;
    FILE *outfile;


    infile = fopen("lab5.dat", "r");//infile opening with error checking 
    if(infile == NULL){
        printf("Error no file\n");
    }

    outfile = fopen("lab5.txt", "w");//output opening with error checking 
    if(outfile == NULL){
        printf("Error no file\n");
    }

    fprintf(outfile, "\nElijah ******.  Lab 5. \n\n");//print name
    fprintf(outfile, "Area of Inscribed Polygons \n\n");
    fprintf(outfile, " Sides    Radius      Area   \n");
    fprintf(outfile, "-------  --------   -------- \n");

    while(fscanf(infile, "%lf %lf", &nsides, &radius)==2){ //while loop while there are two values per line iterate through values    
    double value = sin(2*M_PI/nsides);
    double polygon_area = (0.5)*nsides*radius*radius*value;//math of polygon area
    fprintf(outfile,"%6.2lf    %6.2lf  %10.3lf \n",nsides,radius,polygon_area);//print answer to document
    }

    fprintf(outfile, "\n\n");//print pagebreak

    fclose(infile);//close infile and outfile
    fclose(outfile);
    return EXIT_SUCCESS;
}
/*---------------------------------------------------*/
