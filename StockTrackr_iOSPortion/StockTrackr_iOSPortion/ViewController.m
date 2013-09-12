//
//  ViewController.m
//  StockTrackr_iOSPortion
//
//  Created by Janelle Salisbury on 9/10/13.
//  Copyright (c) 2013 Janelle Salisbury. All rights reserved.
//

#import "ViewController.h"
#import <sqlite3.h>


@interface ViewController ()
{
    NSMutableArray *stocks;
    sqlite3 *stockDB;
    NSString *dbPathString;
    const char *UTF8dbpath;
    
}

@end

@implementation ViewController
@synthesize stocksTableView;


- (void)viewDidLoad
{
    
    [super viewDidLoad];
    
    stocks = [[NSMutableArray alloc]init];
    [[self stocksTableView]setDelegate:self];
    [[self stocksTableView]setDataSource:self];
    [self openDB];
    
    
    NSArray *dirPaths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    if(dirPaths !=nil)
    {
        NSString *docsDirectory = [dirPaths objectAtIndex:0];
        NSString *dbName = @"stock.db";
        
        dbPathString = [docsDirectory stringByAppendingPathComponent:dbName];
        NSLog(@"path = %@", dbPathString);
        
        const char *databasePath = [dbPathString UTF8String];
        if(sqlite3_open(databasePath, &stockDB) == SQLITE_OK)
        {
            //created successfully
            
            //CREATE TABLE STATEMENT
            const char *sql_statement = "CREATE TABLE IF NOT EXISTS STOCK_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, PRICE INTEGER)";
            char *error;
            if(sqlite3_exec(stockDB, sql_statement, NULL, NULL, &error) == SQLITE_OK)
            {
                
            }
            [self addStocks];
            sqlite3_close(stockDB);
            
        }
    }
    
}
-(void) openDB{
    if (sqlite3_open([dbPathString UTF8String], &stockDB)==SQLITE_OK) {
        sqlite3_close(stockDB);
        
    }
}
-(void) addStocks
{
    char *error;
    if(sqlite3_open([dbPathString UTF8String], &stockDB) == SQLITE_OK){
        NSString *insertStmt = [NSString stringWithFormat:@"INSERT INTO STOCK_TABLE (NAME, PRICE) SELECT 'Apple, Inc' as NAME,  '487.115' as PRICE UNION SELECT 'Bank of America Corp', '14.115' UNION SELECT 'Facebook, Inc', '41.31' UNION SELECT 'General Electric Co', '23.1' UNION SELECT 'Intel Corp', '21.97' UNION SELECT 'Sirius XM Radio Inc', '3.58' "];
        
        const char *insert_stmt = [insertStmt UTF8String];
        
        if(sqlite3_exec(stockDB, insert_stmt, NULL, NULL, &error) == SQLITE_OK){
            Stock *stock = [[Stock alloc]init];
    
            [stocks addObject:stock];
            
            
        }
        sqlite3_close(stockDB);
    }
}


-(NSInteger) numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return [stocks count];
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = @"Cell";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];
    
    if (!cell) {
        cell = [[UITableViewCell alloc]initWithStyle:UITableViewCellStyleSubtitle reuseIdentifier:CellIdentifier];
    }
    
    Stock *arrayStock = [stocks objectAtIndex:indexPath.row];
    
    cell.textLabel.text = arrayStock._name;
    cell.detailTextLabel.text = arrayStock._price;
    
    
    return cell;
}

-(IBAction)allStocksButton:(id)sender{
    NSLog(@"function ran");
    sqlite3_stmt *stmt;
    
    if(sqlite3_open([dbPathString UTF8String], &stockDB) == SQLITE_OK){
        [stocks removeAllObjects];
        
        NSString *querySql = [NSString stringWithFormat:@"SELECT * FROM STOCK_TABLE"];
        const char* query_sql = [querySql UTF8String];
        
        if(sqlite3_prepare(stockDB, query_sql, -1, &stmt, NULL) ==SQLITE_OK){
            while (sqlite3_step(stmt) == SQLITE_ROW) {
                NSString *name = [[NSString alloc]initWithUTF8String:(const char *)sqlite3_column_text(stmt, 1)];
                
                NSString *price = [[NSString alloc]initWithUTF8String:(const char *)sqlite3_column_text(stmt, 2)];
                
                Stock *stock = [[Stock alloc]init];
                [stock setName:name];
                [stock setPrice:price];
                [stocks addObject:stock];
                
            }
        }
    }
    [[self stocksTableView] reloadData];
}

-(IBAction)lessThanButton:(id)sender{
    NSLog(@"function worked");
    sqlite3_stmt *stmt;
    
    if(sqlite3_open([dbPathString UTF8String], &stockDB) == SQLITE_OK){
        [stocks removeAllObjects];
        
        NSString *querySql = [NSString stringWithFormat:@"SELECT * FROM STOCK_TABLE WHERE Price IN (14.115, 41.31, 23.1, 21.97, 3.58)"];
        const char* query_sql = [querySql UTF8String];
        
        if(sqlite3_prepare(stockDB, query_sql, -1, &stmt, NULL) ==SQLITE_OK){
            while (sqlite3_step(stmt) == SQLITE_ROW) {
                NSString *name = [[NSString alloc]initWithUTF8String:(const char *)sqlite3_column_text(stmt, 1)];
                
                NSString *price = [[NSString alloc]initWithUTF8String:(const char *)sqlite3_column_text(stmt, 2)];
                
                Stock *stock = [[Stock alloc]init];
                [stock setName:name];
                [stock setPrice:price];
                [stocks addObject:stock];
                
            }
        }
    }
    [[self stocksTableView] reloadData];
    
}

-(IBAction)moreThanButton:(id)sender{
    NSLog(@"yup, I work");
    sqlite3_stmt *stmt;
    
    if(sqlite3_open([dbPathString UTF8String], &stockDB) == SQLITE_OK){
        [stocks removeAllObjects];
        
        NSString *querySql = [NSString stringWithFormat:@"SELECT * FROM STOCK_TABLE WHERE Price IN (487.115)"];
        const char* query_sql = [querySql UTF8String];
        
        if(sqlite3_prepare(stockDB, query_sql, -1, &stmt, NULL) ==SQLITE_OK){
            while (sqlite3_step(stmt) == SQLITE_ROW) {
                NSString *name = [[NSString alloc]initWithUTF8String:(const char *)sqlite3_column_text(stmt, 1)];
                
                NSString *price = [[NSString alloc]initWithUTF8String:(const char *)sqlite3_column_text(stmt, 2)];
                
                Stock *stock = [[Stock alloc]init];
                [stock setName:name];
                [stock setPrice:price];
                [stocks addObject:stock];
                
            }
        }
    }
    [[self stocksTableView] reloadData];
    
}
@end
