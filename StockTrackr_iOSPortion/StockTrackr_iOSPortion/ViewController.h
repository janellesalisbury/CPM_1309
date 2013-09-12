//
//  ViewController.h
//  StockTrackr_iOSPortion
//
//  Created by Janelle Salisbury on 9/10/13.
//  Copyright (c) 2013 Janelle Salisbury. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Stock.h"
#import <sqlite3.h>

@interface ViewController : UIViewController <UITableViewDelegate, UITableViewDelegate>

@property(strong, nonatomic) IBOutlet UITableView *stocksTableView;

-(IBAction)allStocksButton:(id)sender;
-(IBAction)lessThanButton:(id)sender;
-(IBAction)moreThanButton:(id)sender;


@end
