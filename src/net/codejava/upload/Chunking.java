package net.codejava.upload;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class Chunking {
	
	public static short File_size(long size)
	{
		if(size>0 && size<50000)
			return 3000;
		else if(size>=50000 && size<75000)
			return 5000;
		else if(size>=75000 && size< 95000)
			return 7000;
		else if(size>=95000 && size<100000)
			return 10000;
		else if(size>100000)
			return 10000;
		else 
			return 3000;
	}
		
	public static  void chunk(String FILE_NAME ,AWSCredentials credentials,String name,String uname,InputStream is,long size) throws NoSuchAlgorithmException, IOException, CryptoException {
			
			
			String fname =System.getProperty("java.io.tmpdir");
			AWSUploadUtility s3client = new AWSUploadUtility();
			ObjectMetadata metadata=new ObjectMetadata();
			System.out.println("TEMP  : "+fname);
			MongoClientURI uri = new MongoClientURI("mongodb://Admin123:admin@cluster0-shard-00-00-bsnfe.mongodb.net:27017,cluster0-shard-00-01-bsnfe.mongodb.net:27017,cluster0-shard-00-02-bsnfe.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true");
	    	MongoClient mongoClient = new MongoClient(uri);
		    MongoDatabase database = mongoClient.getDatabase("metadata"); 
			
			//File inputFile = new File(FILE_NAME);
		    System.out.println("FILESIZE"+size);
			InputStream inputStream,fileIS = null;
			FileOutputStream filePart;
			int fileSize = (int) size;
			String key = "ABCDEFKLPOBEUWLM";
		    
			FileWriter csv=new FileWriter(fname+"/"+name+".csv");
			csv.append("Hash");
			csv.append("\n");
			
			//Logic for variable chunks to be written here!!!
			short PART_SIZE =File_size(size);
			
			int nChunks = 0, read = 0, readLength = PART_SIZE;
			byte[] byteChunkPart;
			try {
				inputStream = is;
				while (fileSize > 0)
				{
					if (fileSize <= 5)
						readLength = fileSize;
					
					byteChunkPart = new byte[readLength];
					read = inputStream.read(byteChunkPart, 0, readLength);
					fileSize -= read;
					assert (read == byteChunkPart.length);
					nChunks++;
					File filename1=new File(fname+"/"+ "part"+ Integer.toString(nChunks -1));
					filePart = new FileOutputStream(filename1);
					filePart.write(byteChunkPart);
					
					filePart.flush();
					filePart.close();
					byteChunkPart = null;
					filePart = null;
					
					File f1=new File(filename1.toString());
					
					String hash=toHex(Hash.SHA1.checksum(f1.toString()));
					File f2=new File(fname+"/"+hash);
					PrintWriter pw=new PrintWriter(f2);
					BufferedReader bF1=new BufferedReader(new FileReader(f1));
					String data=null;
					while((data=bF1.readLine())!=null)
						pw.println(data);
					
					pw.close();
					bF1.close();
					f1.delete();
					System.out.println("something !!!!!!!!!!");
					String locate=Dedup.Search1(hash,database);
					
					if(locate != null)
					{
						f2.delete();
						csv.append(locate);
						System.out.println("Dedpulicate");
						System.out.println("HORA HAI !!!!!!!!!!");
					}
					else
					{
						csv.append(hash);
						Dedup.update(hash, database);
						fileIS=new FileInputStream(f2);
						metadata.setContentLength(Long.valueOf(fileIS.available()));
						s3client.uploadfile(credentials,"dhanraj",f2.getName().toString(),fileIS,metadata);
						System.out.println("HORA HAI !!!!!!!!!!");
						f2.delete();
					}
					csv.append("\n");
				}
				csv.close();
								
				File f=new File(fname+"/"+name+".csv");
				fileIS=new FileInputStream(f);
				metadata.setContentLength(Long.valueOf(fileIS.available()));
				s3client.uploadfile(credentials,"metadataa",uname+"/"+f.getName().toString(),fileIS,metadata);
				f.delete();
				
				//-----
			inputStream.close();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
				
	}

	private static String toHex(byte[] checksum) {
		// TODO Auto-generated method stub
		return DatatypeConverter.printHexBinary(checksum);
	}
}